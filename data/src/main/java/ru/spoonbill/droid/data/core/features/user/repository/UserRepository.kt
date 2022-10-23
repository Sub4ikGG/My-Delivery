package ru.spoonbill.droid.data.core.features.user.repository

import ru.spoonbill.droid.data.core.config.network.entity.Response
import ru.spoonbill.droid.data.core.features.user.entity.AuthRequest
import ru.spoonbill.droid.data.core.features.user.entity.AuthResponse
import ru.spoonbill.droid.data.core.features.user.entity.QueueRequest
import ru.spoonbill.droid.data.core.features.user.entity.User

interface UserRepository {

    suspend fun createQueue(request: QueueRequest): Response

    suspend fun updateUser(user: User): User

    suspend fun authenticate(request: AuthRequest): AuthResponse
}