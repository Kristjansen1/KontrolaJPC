package com.example.kontrolajpc.presentation.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kontrolajpc.util.BackPressHandler
import com.example.kontrolajpc.util.dataStore.PreferenceKeys


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    settingsViewmodel: SettingsViewmodel = hiltViewModel(),
    settingsState: SettingsState = hiltViewModel<SettingsViewmodel>().settingsState.value,
) {
    val qcPersonName by settingsViewmodel.qcPErsonName.collectAsState()
    var dialogVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {

    }

    BackPressHandler {
        navController.popBackStack()
    }
    if (dialogVisibility) {
        EnterNameDialog(
            setVisibility = { dialogVisibility = it },
            qcPersonName,
            setValue = {
                settingsViewmodel.writeDataStoreStringToken(
                    PreferenceKeys.QC_PERSON_NAME,
                    it
                )
            }
        )
    }
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Nastavitve",
                        fontSize = 30.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                }
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                thickness = 1.dp,
                color = dividerColor()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            dialogVisibility = !dialogVisibility
                        }


                ) {
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                    ) {
                        Text(
                            text = "Ime Kontrolorja",
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = qcPersonName,
                            modifier = Modifier
                                .padding(top = 5.dp),
                            fontSize = 12.sp,
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun EnterNameDialog(
    setVisibility: (Boolean) -> Unit,
    qcPersonName: String,
    setValue: (String) -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf(qcPersonName)
    }
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        "Ime kontrolorja:",
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = name,
                        onValueChange = {
                            name = it
                        }
                    )
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                setValue(name)
                                setVisibility(false)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Potrdi")

                        }
                        Spacer(modifier = Modifier.width(50.dp))
                        Button(
                            onClick = { setVisibility(false) },
                            modifier = Modifier.weight(1f)

                        ) {
                            Text(text = "Prekliči")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getAnnotatedString(str: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            )
        ) {
            append(str)
        }
    }
}

@Composable
fun dividerColor(): Color {
    return if (isSystemInDarkTheme()) Color.White else Color.Black
}

@Composable
@Preview(showBackground = true)
fun SettingsScreenPreview() {
    SettingsScreen(
        NavHostController(LocalContext.current),
    )
}
