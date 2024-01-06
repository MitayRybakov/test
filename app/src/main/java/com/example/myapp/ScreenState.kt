package com.example.myapp

sealed class ScreenState {
    object Loading : ScreenState()

    object Initial : ScreenState()

    data class Content(val text: String) : ScreenState()

}