package com.wintersoft.notification.notification

import com.wintersoft.notification.notification.dto.NotificationCreateRequestDto
import com.wintersoft.notification.notification.dto.NotificationResponseDto
import com.wintersoft.notification.notification.model.Notification
import com.wintersoft.notification.notification.repository.NotificationRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationRepository: NotificationRepository
) {
    // 조회되는 내용이 없다면, Connection 종료, 아니라면 Connection 30초간 유지
    @GetMapping(value = ["/{receiverId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getNotification(
        @PathVariable("receiverId") receiverId: String
    ): Flux<NotificationResponseDto?> {
        return notificationRepository.findByReceiveUserIdsIn(receiverId)
            .mapNotNull { NotificationResponseDto(it!!, receiverId) }
            .timeout(Duration.ofSeconds(30), Flux.just()) // 스레드를 너무 과도하게 사용하는것을 방지하기 위해 timeout 설정
            .subscribeOn(Schedulers.boundedElastic())
    }

    @PostMapping("/")
    suspend fun createNotification(
        @RequestBody requestDto: NotificationCreateRequestDto
    ): ResponseEntity<Any> {
        // 클래스 생성
        val notification = Notification(
            sendUserId = requestDto.sendUserId,
            receiveUserIds = requestDto.receiveUserIds,
            type = requestDto.type,
            message = requestDto.message,
            generatedUrl = requestDto.generatedUrl,
            url = requestDto.url
        )

        // 저장하기
        val savedNotification = notificationRepository.save(notification).awaitSingle()

        // 리턴
        return ResponseEntity.ok(savedNotification)
    }
}