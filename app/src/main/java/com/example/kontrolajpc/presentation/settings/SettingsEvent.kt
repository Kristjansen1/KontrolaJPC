package com.example.kontrolajpc.presentation.settings

import androidx.datastore.preferences.core.Preferences

sealed interface SettingsEvent {
    data class SetDialogVisibility(val visible: Boolean) : SettingsEvent
}
