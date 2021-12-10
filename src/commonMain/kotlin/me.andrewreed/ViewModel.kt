package me.andrewreed

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BrokenViewModel: ViewModel(), KoinComponent {

    private val processingStuff by inject<ProcessingStuff>()

    init {
        scope.launch {
            state.emit(State.Loading())
            processingStuff.doStuff()
        }
    }
}

abstract class ViewModel {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    val state: MutableStateFlow<State?> = MutableStateFlow(null)

    init {
        scope.launch {
            state.collect {
                println("State Changed $it")
            }
        }
    }
}