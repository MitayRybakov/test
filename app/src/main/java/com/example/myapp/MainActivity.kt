package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sp = SampleResponse(1, "Go", 1.53, false)

        setContent {
            MyAPPTheme {
                MyApp()

            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShow1 by rememberSaveable { mutableStateOf(true) }

    if (shouldShow1) {
        FirstScreen(onContinueClicked = { shouldShow1 = false })
    } else
        Startuem()

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Startuem() {

    Surface(

        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 40.dp)

    )
    {
        CircularProgressIndicator(
            modifier = Modifier.padding(15.dp),
            color = Color.Red

        )

        Text(
            text = work(),
            modifier = Modifier.padding(all = 40.dp)
        )


    }


}



 fun work() :String{
    val sp = SampleResponse(1, "Go", 1.53, false)
    val scope1 = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val scope2 = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val scope3 = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val scope4 = CoroutineScope(Dispatchers.IO + SupervisorJob())
    var results = ""



        scope1.launch {
            val r1 = async { sp.foo1() }
            val result1 = r1.await().toString()
            results += "\n" + result1
            Log.e("!!!", results)
        }

        scope2.launch {
            val r2 = async { sp.foo2() }
            val result2 = r2.await().toString()
            results += "\n" + result2
            Log.e("!!!", results)
        }

        scope3.launch {
            val r3 = async { sp.foo3() }
            val result3 = r3.await().toString()
            results += "\n" + result3
            Log.e("!!!", results)
        }

        scope4.launch {
            val r4 = async { sp.foo4() }
            val result4 = r4.await().toString()
            results += "\n" + result4
            Log.e("!!!", results)
        }

     Log.e("!!!","END \n"+results+"\n")


         return results


    }


    @Composable
    fun FirstScreen(
        onContinueClicked: () -> Unit,
        modifier: Modifier = Modifier
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Дратуте")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Вызов 4-ех  параллельных suspend функций ")
            }
        }
    }


    @Preview(showBackground = true, widthDp = 320, heightDp = 320)
    @Composable
    fun Startueeeeem() {
        MyAPPTheme {
            Startuem()
        }
    }


