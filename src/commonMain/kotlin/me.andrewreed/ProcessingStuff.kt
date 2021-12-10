package me.andrewreed

import kotlinx.coroutines.delay

class ProcessingStuff {

    suspend fun doStuff() {
        delay(5000)
        println("doing stuff")
    }

}