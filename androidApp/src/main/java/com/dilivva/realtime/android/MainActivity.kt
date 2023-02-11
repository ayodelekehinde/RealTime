package com.dilivva.realtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dilivva.realtime.Coordinates
import com.dilivva.realtime.Realtime
import com.dilivva.realtime.Response
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Realtime.connectWithCallback(rememberCoroutineScope()){
                when(it){
                    is Response.Error ->{} //println("ERROR=${it.error}")
                    is Response.Message ->{} //println("Message= ${it.message}")
                }
            }

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView("Hello Android")
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    val viewModel: ViewModel = remember {
        ViewModel()
    }
    val scope = rememberCoroutineScope()


    Column {
        Text(text = text)

        Button(onClick = {
            scope.launch {
                Realtime.sendData(Coordinates("Apata","Ibadan"))
            }

        }) {
            Text(text = "Button")
        }
    }

}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

