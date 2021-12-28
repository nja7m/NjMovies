package com.example.njmovies.Repository

import com.example.njmovies.Network.MovieService

class MovieRepository {
	private var service = MovieService.getInstance()
	private val apiKey = "0919d1e3ed46b1554fccb3477c543055"

	suspend fun getPopular(page: Int = 1) = service.getPopular(apiKey, page)
	suspend fun getNowPlaying(page: Int = 1) = service.getNowPlaying(apiKey, page)
	suspend fun getTopRated(page: Int = 1) = service.getTopRated(apiKey, page)
	suspend fun getUpComing(page: Int = 1) = service.getUpComing(apiKey, page)

}