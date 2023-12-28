package com.example.kontrolajpc.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kontrolajpc.presentation.FaultState
import com.example.kontrolajpc.useCase.FaultEvent
import com.example.kontrolajpc.util.DateUtil

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FaultList(
    padding: PaddingValues,
    state: FaultState,
    onEvent: (FaultEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(padding)
    ) {
        val groupedByDate = state.faultList.groupBy { DateUtil.fromLongToDate(it.datum) }
        groupedByDate.forEach { (header, faultList) ->
            stickyHeader {
                Text(
                    text = header,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold,
                )
            }
            items(
                items = faultList,
                key = { fault ->
                    fault.id
                }
            ) { fault ->
                FaultCard(fault, state, onEvent)
            }
        }
    }
}