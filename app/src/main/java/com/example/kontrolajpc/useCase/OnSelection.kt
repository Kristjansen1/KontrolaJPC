package com.example.kontrolajpc.useCase

import com.example.kontrolajpc.database.model.FaultModel

sealed interface OnSelection {

    data class EnableMultipleSelection(val enableMultipleSelection: Boolean): OnSelection
    data class SetSelected(val selected: Int): OnSelection
    data object ClearSelection: OnSelection

}