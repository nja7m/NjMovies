package com.example.njmovies.View.Activities.MovieDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.Model.TrailerResponse
import com.example.njmovies.Model.TrailerResult
import com.example.njmovies.Network.FirebaseService
import com.example.njmovies.Repository.MovieRepository
import com.example.njmovies.util.ResultListener
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel : ViewModel() {

	private val firebaseAuth = FirebaseAuth.getInstance()
	private val movieRepository = MovieRepository()
	private val firebaseService = FirebaseService()


	fun addMovieToList(id: Long, resultListener: ResultListener) {
		firebaseService.addMovieToList(id, resultListener)
	}

	fun getMovieTrailer(movieId: Long): MutableLiveData<TrailerResult> {
		val liveData = MutableLiveData<TrailerResult>()

		movieRepository.getMovieVideos(movieId).enqueue(object : Callback<TrailerResponse> {
			override fun onResponse(
				call: Call<TrailerResponse>,
				response: Response<TrailerResponse>
			) {
				response.body()?.let { trailerResponse ->
					val result = trailerResponse.results
					val movieTrailer = result.find { it.type == "Trailer" }
					liveData.postValue(movieTrailer)
				}
			}

			override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
				t.printStackTrace()
			}
		})

		return liveData
	}

	fun getMovieList() = firebaseService.getMovieList()
	fun movieExist(id: Long) = firebaseService.movieISExist(id)
	fun removeMovie(id: Long, resultListener: ResultListener) =
		firebaseService.deleteMovieFromList(id, resultListener)

	fun getMovieById(id: Long) = movieRepository.getMovieById(id)

}