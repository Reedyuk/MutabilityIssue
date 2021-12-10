package me.andrewreed

open class State {
    class Loading : State()
    class Data : State()
    class Empty : State()
    class Error(val error: Throwable) : State()
}