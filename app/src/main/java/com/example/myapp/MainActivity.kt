package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.MyAPPTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAPPTheme {
                Startuem()
            }
        }
    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Startuem() {
    var state = remember { mutableStateOf<ScreenState>(ScreenState.Initial) }
    val scope = rememberCoroutineScope()

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 40.dp)
    ) {
        val totshka = state.value
        when (totshka) {
            is ScreenState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(15.dp),
                    color = Color.Red
                )
            }

            is ScreenState.Content -> {
                Text(
                    text = totshka.text,
                    modifier = Modifier.padding(all = 40.dp)
                )
            }

            is ScreenState.Initial -> {
                Button(onClick = {
                    state.value = ScreenState.Loading
                    val reps = Repasitory()
                    scope.launch {
                        val result = reps.summAllRequest()
                        state.value = ScreenState.Content(result)
                    }
                }) {
                    Text(
                        text = "ЖМАК",
                        modifier = Modifier.padding(all = 40.dp)
                    )
                }
            }
        }
    }
}





