package me.andrewreed

import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initialize() {
    startKoin {
        printLogger()
        modules(
            module {
                single { ProcessingStuff() }
           }
        )
    }
}
