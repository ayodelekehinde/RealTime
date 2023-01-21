package com.dilivva.realtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.dilivva.realtime.Coordinates
import com.dilivva.realtime.Realtime
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Realtime.connect(rememberCoroutineScope())

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
    val scope = rememberCoroutineScope()
    Column {
        Text(text = text)

        Button(onClick = {
            scope.launch {
                Realtime.sendData(Coordinates("lat","lon"))
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
