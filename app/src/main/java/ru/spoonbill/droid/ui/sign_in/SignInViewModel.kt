package ru.spoonbill.droid.ui.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.features.user.entity.QueueRequest
import ru.spoonbill.droid.data.core.features.user.repository.UserRepository
import ru.spoonbill.droid.ui.sign_in.SignInViewModel.UiEvent.*

class SignInViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiEventsFlow = MutableSharedFlow<UiEvent>(replay = 0)
    val uiEventsFlow = _uiEventsFlow.asSharedFlow()

    suspend fun sendCodeEvent(phone: String) {
        _uiEventsFlow.emit(SendCodeEvent(phone))
    }

    fun createQueue(phone: String) {
        viewModelScope.launch {
            val event = try {
                val request = QueueRequest(phone)
                val response = userRepository.createQueue(request)

                println("response: $response")
                if(response.status == "Ok") SendCodeSuccessEvent(phone)
                else SendCodeFailureEvent(response.message)

            } catch (e: Exception) {
                SendCodeFailureEvent(e.message)
            }
            _uiEventsFlow.emit(event)
        }
    }

    sealed class UiEvent {
        data class SendCodeEvent(val phone: String) : UiEvent()
        data class SendCodeSuccessEvent(val phone: String) : UiEvent()
        data class SendCodeFailureEvent(val message: String?) : UiEvent()
    }
}