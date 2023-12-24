package com.example.kontrolajpc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kontrolajpc.database.AppDatabase
import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.presentation.FaultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class ViewModel(application: Application): AndroidViewModel(application) {

    private val appDatabase = AppDatabase.getDatabase(application)
    private val _faults = appDatabase.faultDao().getAllFaults().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList()
    )
    private val _state = MutableStateFlow(FaultState())

    val state = combine(_state,_faults) {
        state, faults -> state.copy(
        FaultList = faults
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FaultState())

    fun addFault(fault: FaultModel) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.faultDao().insert(fault)
        }
    }

    fun onEvent(event: FaultEvent) {
        //Log.d("nekiivent",event.toString())
        when (event) {
            is FaultEvent.SaveFault -> {

            }

            is FaultEvent.SetDate -> {
                _state.update { it.copy(
                    date = event.date
                ) }
            }
            is FaultEvent.SetPosel -> {
                _state.update { it.copy(
                    posel = event.posel
                ) }
            }
            is FaultEvent.SetSeriska -> {
                _state.update { it.copy(
                    serijska = event.serijska
                ) }
            }
            is FaultEvent.SetProizvodniNalog -> {
                _state.update { it.copy(
                    proizvodnjiNalog = event.pnalog
                ) }
            }
            is FaultEvent.SetVrstaNapake -> {
                _state.update { it.copy(
                    vrstaNapake = event.vrstaNapake
                ) }
            }
            is FaultEvent.SetOpisNapake -> {
                _state.update { it.copy(
                    opisNapake = event.opisNapake
                ) }
            }

            is FaultEvent.ClearState -> {
                _state.update { it.copy(
                    date = LocalDate.now().toString(),
                    posel = "",
                    serijska = "",
                    proizvodnjiNalog = "",
                    vrstaNapake = 0,
                    opisNapake = ""
                ) }
            }
            is FaultEvent.SetDateDialogShowState -> {
                _state.update { it.copy(
                    dateDialogShowState = event.showDateDialog
                ) }
            }

            else -> {}
        }
    }

}
