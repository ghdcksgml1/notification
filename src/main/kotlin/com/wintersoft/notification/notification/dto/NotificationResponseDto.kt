package com.wintersoft.notification.notification.dto

import com.wintersoft.notification.common.DateDistanceKt
import com.wintersoft.notification.notification.model.Notification
import java.time.LocalDateTime

data class NotificationResponseDto(
    val sendUserId: String, // 알림을 보낸 유저 아이디
    val receiveUserId: String, // 알림을 받는 아이디
    val type: String? = "comment", // 알림 종류 (comment, note, reply, bookmark)
    val message: String, // 알림 내용
    val generatedUrl: String? = null, // 알림의 근원지
    val url: String? = null, // 알림 클릭 시 이동할 주소
    val createdTime: LocalDateTime, // 알림 발생 시간
    val checkedTime: LocalDateTime? = null, // 알림 확인 시간
    val checked: Boolean? = false, // 알림을 확인했는지 안했는지
    val dateDistance: String
) {
    constructor(notification: Notification, receiveUserId: String) : this(
        sendUserId = notification.sendUserId,
        receiveUserId = receiveUserId,
        type = notification.type,
        message = notification.message,
        generatedUrl = notification.generatedUrl,
        url = notification.url,
        createdTime = notification.createdTime,
        checkedTime = notification.checkedTime,
        checked = notification.checked,
        dateDistance = DateDistanceKt.of(notification.createdTime)
    )
}