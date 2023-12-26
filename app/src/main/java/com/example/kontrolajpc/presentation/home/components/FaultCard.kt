package com.example.kontrolajpc.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kontrolajpc.ElectricalFaults
import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.util.DateUtil

@Composable
fun FaultCard(
    fault: FaultModel,
    onClick: (FaultModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(ElectricalFaults.values()[fault.napaka!!].toString())
            Text(DateUtil.fromLongToDate(fault.datum))
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun P() {
    val f = FaultModel(
        datum = 1865613516813,
        serijska = "21651",
        posel = "35168",
        exported = false,
        napaka = 0,
        opomba = "neki"
    )
    FaultCard(fault = f, onClick = {

    })
}
