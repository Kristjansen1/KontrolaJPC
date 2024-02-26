package com.example.kontrolajpc

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.kontrolajpc.navigation.SetupNavGraph
import com.example.kontrolajpc.ui.theme.KontrolaJPCTheme
import dagger.hilt.android.AndroidEntryPoint

fun applog(desc: String,result: String) {
    Log.d(desc,result)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    //private val viewModel: ViewModel by viewModels()
    //val context = LocalContext
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       /* val c = application.applicationContext
        val serviceIntent = Intent(c, ClipboardService::class.java)
        c.startService(serviceIntent)*/


   /*     lifecycleScope.launch(Dispatchers.Main){
            store.getAccessToken.collectLatest() { token ->

            if (token.isEmpty()) {
                applog("token","empty, adding")
                store.saveToken("not empty anymore")
            } else {
                applog("token","not empty: $token")

            }

            }
        }*/
        setContent {



            KontrolaJPCTheme {
                val navController = rememberNavController()
                val viewModel: ViewModel = hiltViewModel()




                SetupNavGraph(
                    navController = navController,
                    viewModel
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KontrolaJPCTheme {
    }
}