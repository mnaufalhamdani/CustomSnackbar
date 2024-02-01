package com.mnaufalhamdani.customsnackbarcompose

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CustomSnackbarState {
    private val _typeMessage = mutableStateOf(TypeMessage.INFO)
    val typeMessage: State<TypeMessage> = _typeMessage

    private val _message = mutableStateOf("unknown Message")
    val message: State<String> = _message

    private val _duration = mutableStateOf(SnackbarDuration.Short)
    val duration: State<SnackbarDuration> = _duration

    private val _paddingBottom = mutableIntStateOf(0)
    val paddingBottom: State<Int> = _paddingBottom

    var updateState by mutableStateOf(false)

    fun showSnackbarMessage(
        typeMessage: TypeMessage,
        message: String = "unknown Message",
        duration: SnackbarDuration = SnackbarDuration.Short,
        paddingBottom: Int = 0
    ) {
        _typeMessage.value = typeMessage
        _message.value = message
        _duration.value = duration
        _paddingBottom.intValue = paddingBottom

        updateState = true
    }

}