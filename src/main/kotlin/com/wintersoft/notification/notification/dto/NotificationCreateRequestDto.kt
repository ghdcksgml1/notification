package com.wintersoft.notification.notification.dto

import java.time.LocalDateTime

data class NotificationCreateRequestDto(
    val sendUserId: String, // 알림을 보낸 유저 아이디
    val receiveUserIds: MutableList<String>, // 알림을 받는 아이디
    val type: String? = "comment", // 알림 종류 (comment, note, reply, bookmark)
    val message: String, // 알림 내용
    val generatedUrl: String? = null, // 알림의 근원지
    val url: String? = null, // 알림 클릭 시 이동할 주소
)
