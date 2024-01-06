package com.example.myapp

import kotlinx.coroutines.delay

class SampleResponse(param1: Int, param2: String, param3: Double, param4: Boolean) {
    private var pole1 = param1
    private var pole2 = param2
    private var pole3 = param3
    private var pole4 = param4
    var listHelp = listOf<Int>(1, 2, 3, 4, 5, 6)

    override fun toString(): String {
        return pole1.toString() + " " + pole2 + " " + pole3.toString() + " " + pole4.toString()
    }

    suspend fun foo1(): List<Int> {
        delay(1000L)
        return listHelp
    }

    suspend fun foo2(): Boolean {
        delay(2000L)
        return pole4
    }

    suspend fun foo3(): Double {
        delay(3000L)
        return pole3
    }

    suspend fun foo4(): SampleResponse {
        delay(4000L)
        return this

    }

    private suspend fun callAllSus() {


    }

    public fun startWork() {


    }

}