package ru.spoonbill.droid.data.core.features.user.manager

import android.content.Context
import android.content.SharedPreferences
import ru.spoonbill.droid.data.core.features.user.entity.User

internal class UserManagerImpl(
    context: Context
) : UserManager {

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun exists(): Boolean = preferences.contains(KEY_USER_TOKEN)

    override fun save(user: User): Int = try {
        preferences.edit().apply {
            putString(KEY_USER_TOKEN, user.token)
            apply()
        }
        RESULT_SUCCESS
    } catch (e: Exception) {
        RESULT_FAILURE
    }

    override fun get(): User? {
        val token = preferences.getString(KEY_USER_TOKEN, DEFAULT_TOKEN_VALUE) ?: DEFAULT_TOKEN_VALUE
        return if (token == DEFAULT_TOKEN_VALUE) null else User(id = 0, token = token)
    }

    companion object {
        private const val PREFERENCES_NAME = "ru.spoonbill.droid.USER_PREFERENCES"
        private const val KEY_USER_TOKEN = "ru.spoonbill.droid.USER_TOKEN"
        private const val DEFAULT_TOKEN_VALUE = "ru.spoonbill.droid.DEFAULT_USER_TOKEN"

        private const val RESULT_SUCCESS = 1
        private const val RESULT_FAILURE = -1
    }
}