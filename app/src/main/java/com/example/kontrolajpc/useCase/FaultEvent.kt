package com.example.kontrolajpc.useCase

sealed interface FaultEvent {

    object SaveFault: FaultEvent

    data class SetDate(val date: String): FaultEvent
    data class SetPosel(val posel: String): FaultEvent
    data class SetSeriska(val serijska: String): FaultEvent
    data class SetProizvodniNalog(val pnalog: String): FaultEvent
    data class SetVrstaNapake(val vrstaNapake: Int): FaultEvent
    data class SetOpisNapake(val opisNapake: String): FaultEvent
    object ClearState : FaultEvent

    data class SetDateDialogShowState(val showDateDialog: Boolean): FaultEvent

}
