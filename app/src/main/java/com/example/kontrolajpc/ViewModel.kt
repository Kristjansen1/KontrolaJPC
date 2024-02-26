package com.example.kontrolajpc

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kontrolajpc.database.FaultDao
import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.poi.Poi
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.presentation.SelectionState
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.useCase.OnSelection
import com.example.kontrolajpc.util.Const
import com.example.kontrolajpc.util.DateUtil
import com.example.kontrolajpc.util.dataStore.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ViewModel @Inject constructor(
    private val dao: FaultDao,
    private val application: Application,
    private val dataStore: UserStore
) : AndroidViewModel(application) {

    init {

        applog("viewmodel", "viewmodel init")
    }



    private val _faults = dao.getAllFaults().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(), mutableStateListOf()
    )
    private val _state = MutableStateFlow(FaultState())


    val state = combine(_state, _faults) { state, faults ->
        state.copy(
            faultList = faults
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FaultState())

    private val _selectionState = MutableStateFlow(SelectionState())

    val selectionState = _selectionState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SelectionState()
    )
    //val selectionState = _selectionState.asStateFlow()


    fun onSelection(action: OnSelection) {
        when (action) {
            is OnSelection.EnableMultipleSelection -> {
                _selectionState.update {
                    it.copy(
                        enableMultipleSelection = action.enableMultipleSelection
                    )

                }
            }

            is OnSelection.SetSelected -> {
                val x = selectionState.value.listOfSelectedFaultIds
                if (action.selected != -1) {
                    if (x.contains(action.selected)) {
                        x.remove(action.selected)
                    } else {
                        x.add(action.selected)
                    }
                } else {
                    x.clear()
                }


                _selectionState.update {
                    it.copy(
                        listOfSelectedFaultIds = x,
                    )
                }
            }

            is OnSelection.ClearSelection -> {
                onSelection(OnSelection.EnableMultipleSelection(false))
                onSelection(OnSelection.SetSelected(-1))
            }

            else -> {}
        }
    }

    fun onEvent(event: FaultEvent) {
        when (event) {

            is FaultEvent.DeleteExported -> {
                viewModelScope.launch {
                    dao.deleteExported()
                }
            }
            is FaultEvent.ExportFaults -> {
                viewModelScope.launch {
                    val faultList =
                        dao.findByID(selectionState.value.listOfSelectedFaultIds)
                    if (faultList.isNotEmpty()) {
                        val selectedIds = selectionState.value.listOfSelectedFaultIds
                        dao.setExportedById(selectedIds, true)
                        Poi.export(faultList, application,dataStore)
                        onSelection(OnSelection.ClearSelection)
                    }
                }
            }

            is FaultEvent.SetEnableEdit -> {
                _state.update {
                    it.copy(
                        enableEdit = !_state.value.enableEdit
                    )
                }
            }

            is FaultEvent.SetFault -> {
                _state.update {
                    it.copy(
                        id = event.fault.id,
                        date = event.fault.datum,
                        posel = event.fault.posel!!,
                        serijska = event.fault.serijska!!,
                        proizvodniNalog = event.fault.proizvodniNalog!!,
                        vrstaNapake = event.fault.vrstaNapake!!,
                        opisNapake = event.fault.opisNapake!!,

                        )
                }
            }

            is FaultEvent.SaveFault -> {
                val id = state.value.id
                val date = state.value.date
                val posel = state.value.posel
                val serijska = state.value.serijska
                val proizvodniNalog = state.value.proizvodniNalog
                val vrstaNapake = state.value.vrstaNapake
                val opisNapake = state.value.opisNapake
                val exported = state.value.exported

                val fault = if (id > 0) {
                    applog("upsert", id.toString())
                    FaultModel(
                        id = id,
                        datum = date,
                        posel = posel,
                        serijska = serijska,
                        proizvodniNalog = proizvodniNalog,
                        vrstaNapake = vrstaNapake,
                        opisNapake = opisNapake,
                        exported = exported
                    )
                } else {
                    FaultModel(
                        datum = date,
                        posel = posel,
                        serijska = serijska,
                        proizvodniNalog = proizvodniNalog,
                        vrstaNapake = vrstaNapake,
                        opisNapake = opisNapake,
                        exported = exported
                    )
                }


                viewModelScope.launch(Dispatchers.IO) {
                    dao.upsert(fault)
                }
            }

            is FaultEvent.DeleteFault -> {
                viewModelScope.launch(Dispatchers.IO) {
                   dao.delete(event.fault)
                }
            }

            is FaultEvent.BulkDelete -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.deleteById(event.idsToDelete)
                    onSelection(OnSelection.ClearSelection)
                }
            }

            is FaultEvent.SetDate -> {
                _state.update {
                    Log.d(
                        "izbrano",
                        DateUtil.fromLongToDate(event.date, Const.DATE_FORMAT_UI) + "viewmodel"
                    )
                    it.copy(
                        date = event.date
                    )
                }
            }

            is FaultEvent.SetPosel -> {
                applog("applog", "onevent")
                _state.update {
                    it.copy(
                        posel = event.posel
                    )
                }
            }

            is FaultEvent.SetSeriska -> {
                _state.update {
                    it.copy(
                        serijska = event.serijska
                    )
                }
            }

            is FaultEvent.SetProizvodniNalog -> {
                _state.update {
                    it.copy(
                        proizvodniNalog = event.pnalog
                    )
                }
            }

            is FaultEvent.SetVrstaNapake -> {
                _state.update {
                    it.copy(
                        vrstaNapake = event.vrstaNapake
                    )
                }
            }

            is FaultEvent.SetOpisNapake -> {
                _state.update {
                    it.copy(
                        opisNapake = event.opisNapake
                    )
                }
            }

            is FaultEvent.ClearState -> {
                _state.update {
                    it.copy(
                        id = 0,
                        date = DateUtil.cDate(),
                        posel = "",
                        serijska = "",
                        proizvodniNalog = "",
                        vrstaNapake = 0,
                        opisNapake = "",
                        exported = false
                    )
                }
            }

            is FaultEvent.SetDateDialogShowState -> {
                _state.update {
                    it.copy(
                        dateDialogShowState = event.showDateDialog
                    )
                }
            }

            else -> {}
        }
    }

    override fun onCleared() {
        applog("viewmodel", "viewmodel cleared")
    }

}
