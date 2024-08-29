package com.app.switchapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isEgoChecked = MutableLiveData(true)
    val isEgoChecked: LiveData<Boolean> get() = _isEgoChecked

    private val _switchStates = MutableLiveData<Map<String, Boolean>>()
    val switchStates: LiveData<Map<String, Boolean>> get() = _switchStates

    private val _isBottomNavVisible = MutableLiveData(false)
    val isBottomNavVisible: LiveData<Boolean> get() = _isBottomNavVisible

    init {
        _switchStates.value = mapOf(
            "giving" to false,
            "respect" to false,
            "kindness" to false,
            "optimism" to false,
            "happiness" to false
        )
    }

    fun setEgoChecked(isChecked: Boolean) {
        _isEgoChecked.value = isChecked
        _isBottomNavVisible.value = !isChecked

        if (isChecked) {
            disableOtherSwitches()
        }
    }

    private fun disableOtherSwitches() {
        _switchStates.value = _switchStates.value?.mapValues { false }
    }

    fun updateSwitchState(switchName: String, isChecked: Boolean) {
        _switchStates.value = _switchStates.value?.toMutableMap()?.apply {
            put(switchName, isChecked)
        }
    }
}
