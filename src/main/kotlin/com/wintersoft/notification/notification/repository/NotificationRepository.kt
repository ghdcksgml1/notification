package com.wintersoft.notification.notification.repository

import com.wintersoft.notification.notification.model.Notification
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Tailable
import reactor.core.publisher.Flux

interface NotificationRepository : ReactiveMongoRepository<Notification, String> {

    @Tailable
    fun findByReceiveUserIdsIn(userIds: String): Flux<Notification?>
}