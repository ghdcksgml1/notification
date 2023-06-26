package com.wintersoft.notification.notification.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "notification")
data class Notification(
    @Id
    val id: String? = null,
    val sendUserId: String, // 알림을 보낸 유저 아이디
    val receiveUserIds: MutableList<String>, // 알림을 받는 아이디
    val type: String? = "comment", // 알림 종류 (comment, note, reply, bookmark)
    val message: String, // 알림 내용
    val generatedUrl: String? = null, // 알림의 근원지
    val url: String? = null, // 알림 클릭 시 이동할 주소
    val createdTime: LocalDateTime = LocalDateTime.now(), // 알림 발생 시간
    val checkedTime: LocalDateTime? = null, // 알림 확인 시간
    val checked: Boolean? = false, // 알림을 확인했는지 안했는지
)