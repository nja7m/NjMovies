package com.example.njmovies.View.Activities.Home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.njmovies.Model.MovieCategory
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.util.MoviePagingSource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {
	private val firebaseAuth = FirebaseAuth.getInstance()

	fun getMovies(movieCategory: MovieCategory): Flow<PagingData<MovieResult>> {
		Log.d(TAG, "getMovies: $movieCategory")

		return Pager(PagingConfig(25), pagingSourceFactory = {
			MoviePagingSource(movieCategory)
		}).flow
	}

	fun logout() = firebaseAuth.signOut()
}
