package com.app.switchapp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    fun setEgoChecked(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(
            isEgoChecked = isChecked,
            isBottomNavVisible = !isChecked
        )
    }
    fun updateBottomBarCount(count: Int) {
        _uiState.value = _uiState.value.copy(bottomBarCount = count)
    }

    fun setGivingChecked(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isGivingChecked = isChecked)
    }

    fun setRespectChecked(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isRespectChecked = isChecked)
    }

    fun setKindnessChecked(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isKindnessChecked = isChecked)
    }

    fun setOptimismChecked(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isOptimismChecked = isChecked)
    }

    fun setHappinessChecked(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isHappinessChecked = isChecked)
    }
}