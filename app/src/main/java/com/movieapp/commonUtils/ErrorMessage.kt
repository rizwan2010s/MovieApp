package com.movieapp.commonUtils

//enum class for error message check on api Exception
enum class ErrorMessage(var errorMessage: String) {
    ERROR_UNKNOWN("UnKnown Error"),
    ERROR_INTERNET("Check your Internet connection"),
    ERROR_GENERIC("Some error Occurred.")
}