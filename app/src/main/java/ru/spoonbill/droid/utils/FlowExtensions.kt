package ru.spoonbill.droid.utils

import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> MutableSharedFlow<T>.singleEventFlow() = MutableSharedFlow<T>(replay = 0)