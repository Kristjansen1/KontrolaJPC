package com.example.kontrolajpc.useCase

import com.example.kontrolajpc.database.model.FaultModel

sealed interface FaultEvent {
    data object SaveFault : FaultEvent
    data class DeleteFault(val fault: FaultModel) : FaultEvent
    data class SetDate(val date: Long) : FaultEvent
    data class SetPosel(val posel: String) : FaultEvent
    data class SetSeriska(val serijska: String) : FaultEvent
    data class SetProizvodniNalog(val pnalog: String) : FaultEvent
    data class SetVrstaNapake(val vrstaNapake: Int) : FaultEvent
    data class SetOpisNapake(val opisNapake: String) : FaultEvent
    data object ClearState : FaultEvent
    data class SetDateDialogShowState(val showDateDialog: Boolean) : FaultEvent

    data class SetShowHideEditIconId(val faultId: Int) : FaultEvent

}
