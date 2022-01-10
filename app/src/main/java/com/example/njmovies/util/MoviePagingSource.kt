package com.example.njmovies.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.njmovies.Model.MovieCategory
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.Repository.MovieRepository
import retrofit2.HttpException
import java.io.IOException

//regular class implements pagingSource from paging library
class MoviePagingSource constructor(
	private val movieCategory: MovieCategory,
	private val query: String? = null
) : PagingSource<Int, MovieResult>() {

	private val repository = MovieRepository()

	// Function to load data from API
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
		val page = params.key ?: 1
		return try {
			val response = when (movieCategory) {
				MovieCategory.POPULAR -> repository.getPopular(page)
				MovieCategory.NOW_PLAYING -> repository.getNowPlaying(page)
				MovieCategory.TOP_RATED -> repository.getTopRated(page)
				MovieCategory.UPCOMING -> repository.getUpComing(page)
				MovieCategory.SEARCH -> query?.let { repository.searchMovies(it, page) }
			}

			LoadResult.Page(
				response!!.results, prevKey = if (page == 1) null else page - 1,
				nextKey = if (response.results.isEmpty()) null else page + 1
			)
		} catch (exception: IOException) {
			return LoadResult.Error(exception)
		} catch (exception: HttpException) {
			return LoadResult.Error(exception)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
		}
	}
}