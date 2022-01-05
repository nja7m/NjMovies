package com.example.njmovies.Model

data class User(
	var name: String,
	var email: String,
	var password: String,
	var movieList: MutableList<Long> = mutableListOf()
)