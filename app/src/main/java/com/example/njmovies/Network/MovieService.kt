package com.example.njmovies.Network

import com.example.njmovies.Model.MovieResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
	@GET("movie/popular")
	suspend fun getPopular(
		@Query("api_key") apiKey: String,
		@Query("page") page: Int
	): MovieResponse

	@GET("movie/now_playing")
	suspend fun getNowPlaying(
		@Query("api_key") apiKey: String,
		@Query("page") page: Int
	): MovieResponse

	@GET("movie/top_rated")
	suspend fun getTopRated(
		@Query("api_key") apiKey: String,
		@Query("page") page: Int
	): MovieResponse

	@GET("movie/upcoming")
	suspend fun getUpComing(
		@Query("api_key") apiKey: String,
		@Query("page") page: Int
	): MovieResponse


	companion object {
		private var instance: MovieService? = null

		fun getInstance(): MovieService {
			if (instance == null) {
				instance = Retrofit.Builder()
					.baseUrl("https://api.themoviedb.org/3/")
					.addConverterFactory(GsonConverterFactory.create())
					.build()
					.create(MovieService::class.java)
			}

			return instance!!
		}
	}

}