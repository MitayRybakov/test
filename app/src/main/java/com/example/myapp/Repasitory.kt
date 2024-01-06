package com.example.myapp

import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class Repasitory {

    private suspend fun request1(): List<Int> =
        withContext(Dispatchers.Default) {
            delay(7000L)
            Log.e("!!!", "Запрос номер 1 = "+Thread.currentThread().id.toString())
            listOf(1, 2, 3, 4)
        }


    private suspend fun request2(): Boolean {
        return withContext(Dispatchers.IO) {
            delay(8000L)
            Log.e("!!!", "Запрос номер 2 = "+Thread.currentThread().id.toString())
            true
        }
    }

    @DelicateCoroutinesApi
    private suspend fun request3(): Double {
        return withContext(newSingleThreadContext("MyOwnThread")) {
            delay(3000L)
            Log.e("!!!", "Запрос номер 3 = "+Thread.currentThread().name)
            2.28
        }
    }

    private suspend fun request4(): SampleResponse {
        return withContext(Dispatchers.Unconfined) {
            delay(4000L)
            Log.e("!!!", "Запрос номер 4 = "+Thread.currentThread().id.toString())
            SampleResponse(1, "aaa", 2.3, true)
        }
    }

   /*
   private suspend fun request5(): SampleResponse {
          return withContext(
            context = Dispatchers.Unconfined,
            block = {
                delay(4000L)
                SampleResponse(1, "aaa", 2.3, true)
            }
        )
    }
    */


    @OptIn(DelicateCoroutinesApi::class)
    suspend fun summAllRequest(): String {
        var results = ""

        coroutineScope() {
            val respon1 = async { request1() }
            val respon2 = async { request2() }
            val respon3 = async { request3() }
            val respon4 = async { request4() }

            results = respon1.await().toString() +
                    respon2.await() +
                    respon3.await().toString() +
                    respon4.await().toString()
        }

        return results
    }
}