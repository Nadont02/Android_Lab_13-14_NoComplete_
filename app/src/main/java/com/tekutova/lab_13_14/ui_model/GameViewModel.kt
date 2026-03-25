package com.tekutova.lab_13_14.ui_model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.tekutova.lab_13_14.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        resetGame()
    }

    fun resetGame(){

    }
}