package com.example.njmovies.View.Activities.MovieDetails

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.databinding.ActivityMovieDetailsBinding
import com.example.njmovies.util.ResultListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class MovieDetailsActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMovieDetailsBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
		val viewModel: MovieDetailsViewModel by viewModels()
		setContentView(binding.root)
		setSupportActionBar(binding.toolbarMovieDetails)
		binding.toolbarMovieDetails.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)
		binding.toolbarMovieDetails.setNavigationOnClickListener {
			finish()
		}


		val movie = intent.getSerializableExtra("Movie") as MovieResult

		binding.movieTitle.text = movie.title
		binding.movieOverview.text = movie.overview
		binding.movieReleaseDate.text = movie.release_date
		binding.movieRating.rating = (movie.vote_average / 2).toFloat()
		Glide.with(this)
			.load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
			.transform(CenterCrop())
			.into(binding.moviePoster)

		viewModel.getMovieTrailer(movie.id.toLong()).observe(this) { trailerResult ->
			binding.youtubePlayerView.addYouTubePlayerListener(object :
				AbstractYouTubePlayerListener() {
				override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
					youTubePlayer.loadVideo(trailerResult.key, 0f)
				}
			})
		}

		viewModel.movieExist((movie.id).toLong()).observe(this, {
			var isAdded = it
			Log.d("MovieDetailsActivity", "onCreate: $it")
			if (isAdded) {
				binding.addToMyListButton.text = "ADDED"
			} else {
				binding.addToMyListButton.text = "ADD TO MY LIST"
			}
			binding.addToMyListButton.setOnClickListener {
				if (isAdded) {
					Toast.makeText(this, "Removing movie", Toast.LENGTH_SHORT).show()
					viewModel.removeMovie((movie.id).toLong(), object : ResultListener {
						override fun onSuccess() {
							binding.addToMyListButton.text = "ADD TO MY LIST"
							Log.d("MovieDetailsActivity", "onSuccess: $isAdded")
							Toast.makeText(
								this@MovieDetailsActivity,
								"Movie has been removed from My List",
								Toast.LENGTH_SHORT
							).show()

						}

						override fun onFailure(error: Throwable) {
							Toast.makeText(
								this@MovieDetailsActivity,
								"failed to remove movie from my list ",
								Toast.LENGTH_SHORT
							).show()
						}

					})
				} else {
					Toast.makeText(this, "Adding movie", Toast.LENGTH_SHORT).show()
					viewModel.addMovieToList((movie.id).toLong(), object : ResultListener {
						override fun onSuccess() {
							binding.addToMyListButton.text = "ADDDED"
							Log.d("MovieDetailsActivity", "onSuccess: $isAdded")
							Toast.makeText(
								this@MovieDetailsActivity,
								"Movie ${movie.id} has been added to your list",
								Toast.LENGTH_SHORT
							).show()

						}

						override fun onFailure(error: Throwable) {
							Toast.makeText(
								this@MovieDetailsActivity,
								"Failed to add movie to your list",
								Toast.LENGTH_SHORT
							).show()
						}

					})
				}
				isAdded = !isAdded
			}
		})


//            viewModel.getMovieList().observeForever{
//
//            }
//			var myList = viewModel.getMovieList()
//
//			viewModel.getMovieList().observe(viewLifecycleOwner, { myList ->
////				var isAdded = myList.contains(movie.id)
////				println(isAdded)
////				println(movie.id)
//				var check: Boolean
//				if (myList.contains(movie.id)) {
//					binding.addToMyListButton.text = "ADDED"
//				} else {
//					binding.addToMyListButton.text = "ADD TO MY LIST"
//				}
////				if (isAdded) {
////					binding.addToMyListButton.text = "ADDED"
//
//
//
//				binding.addToMyListButton.setOnClickListener {
//
//					if (myList.contains(movie.id)) {
////						binding.addToMyListButton.text = "ADD TO MY LIST"
//						//TODO remove from list
//					} else {
//						viewModel.addMovieToList(movie.id, object : ResultListener {
//							override fun onSuccess() {
//								binding.addToMyListButton.text = "ADDDED"
//								Toast.makeText(
//									activity,
//									"Movie ${movie.id} has been added to your list",
//									Toast.LENGTH_SHORT
//								).show()
//
//							}
//
//							override fun onFailure(error: Throwable) {
//								Toast.makeText(
//									activity,
//									"Failed to add movie to your list",
//									Toast.LENGTH_SHORT
//								).show()
//							}
//
//						})
//					}
//				}
//
//
//			})


//
//
//		binding.addToMyListButton.setOnClickListener {
//
//			viewModel.addMovieToList((movie.id).toLong(), object : ResultListener {
//				override fun onSuccess() {
//
//
//					binding.addToMyListButton.text = "ADDED"
//
//					Toast.makeText(
//						this@MovieDetailsActivity,
//						"Movie ${movie.id} has been added to your list",
//						Toast.LENGTH_SHORT
//					).show()
//				}
//
//
//				override fun onFailure(error: Throwable) {
//					Toast.makeText(
//						this@MovieDetailsActivity,
//						"Failed to add movie to your list",
//						Toast.LENGTH_SHORT
//					).show()
//				}
//
//			})
//		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.movie_details_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onDestroy() {
		super.onDestroy()
		binding.youtubePlayerView.release()
	}

}

