package com.example.njmovies.Model

data class Genre(
	val id: Int,
	val name: String
) {
	override fun toString(): String {
		return "Genre($id, \"$name\")"
	}
}