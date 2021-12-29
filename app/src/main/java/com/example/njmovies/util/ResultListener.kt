package com.example.njmovies.util

interface ResultListener {
	fun onSuccess()
	fun onFailure(error: Throwable)
}