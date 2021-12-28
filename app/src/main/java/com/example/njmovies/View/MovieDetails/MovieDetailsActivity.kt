package com.example.njmovies.View.MovieDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.njmovies.Model.Genre
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.databinding.ActivityMovieDtailsBinding
import com.example.njmovies.util.findGenreById
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		var binding = ActivityMovieDtailsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		var movie = intent.getSerializableExtra("movie") as MovieResult
		binding.movieTitleTextView.text = movie.title
		binding.movieOverviewTextView.text = movie.overview
		binding.movieDetailsReleaseDateTextView.text = movie.release_date
//		 binding.genreTextView.text = movie.genre_ids.toString()
		val genresList = mutableListOf<Genre>()
		var genreListStr = ""
		movie.genre_ids.forEach { id ->
			val genre = findGenreById(id)
			if (genre != null) {
				genresList.add(genre)
				genreListStr += "${genre.name} "
			}
		}

		binding.genreTextView.text = genreListStr


		Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
			.into(binding.movieDetailsPosterImageView)


	}
}