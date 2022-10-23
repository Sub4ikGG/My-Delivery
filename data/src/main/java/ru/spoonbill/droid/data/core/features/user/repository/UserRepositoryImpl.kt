package ru.spoonbill.droid.data.core.features.user.repository

import io.ktor.client.call.*
import ru.spoonbill.droid.data.core.config.network.Paths
import ru.spoonbill.droid.data.core.config.network.client
import ru.spoonbill.droid.data.core.config.network.entity.Response
import ru.spoonbill.droid.data.core.config.network.post
import ru.spoonbill.droid.data.core.config.network.put
import ru.spoonbill.droid.data.core.features.user.entity.AuthRequest
import ru.spoonbill.droid.data.core.features.user.entity.AuthResponse
import ru.spoonbill.droid.data.core.features.user.entity.QueueRequest
import ru.spoonbill.droid.data.core.features.user.entity.User

class UserRepositoryImpl : UserRepository {
    override suspend fun createQueue(request: QueueRequest): Response {
        return client.post(Paths.CREATE_QUEUE, request).body()
    }

    override suspend fun updateUser(user: User): User {
        return client.put(Paths.USER, user).body()
    }

    override suspend fun authenticate(request: AuthRequest): AuthResponse {
        return client.post(Paths.USER, request).body()
    }
}