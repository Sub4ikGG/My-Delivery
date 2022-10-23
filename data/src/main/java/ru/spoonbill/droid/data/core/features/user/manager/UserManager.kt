package ru.spoonbill.droid.data.core.features.user.manager

import ru.spoonbill.droid.data.core.features.user.entity.User

interface UserManager {

    fun exists(): Boolean

    fun save(user: User): Int

    fun get(): User?
}