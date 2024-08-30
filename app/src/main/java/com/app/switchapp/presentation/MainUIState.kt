package com.app.switchapp.presentation

data class UIState(
    val isEgoChecked: Boolean = true,
    val isGivingChecked: Boolean = false,
    val isRespectChecked: Boolean = false,
    val isKindnessChecked: Boolean = false,
    val isOptimismChecked: Boolean = false,
    val isHappinessChecked: Boolean = false,
    val isBottomNavVisible: Boolean = false,
    val bottomBarCount: Int = 0
)

