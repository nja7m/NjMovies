package com.example.njmovies.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

	private lateinit var binding: FragmentMovieDetailsBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?

	): View? {
		// Inflate the layout for this fragment
		val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
		binding = FragmentMovieDetailsBinding.bind(view)

		arguments?.let {
			val movie = it.getSerializable("Movie") as MovieResult

			binding.movieTitle.text = movie.title
			binding.movieOverview.text = movie.overview
			binding.movieReleaseDate.text = movie.release_date
			binding.movieRating.rating = (movie.vote_average / 2).toFloat()
			Toast.makeText(
				activity,
				"Rating: ${(movie.vote_average / 2).toFloat()}",
				Toast.LENGTH_SHORT
			).show()

			Glide.with(this)
				.load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
				.transform(CenterCrop())
				.into(binding.moviePoster)
		}

		return view

	}

	companion object {

		@JvmStatic
		fun newInstance(movie: MovieResult) = MovieDetailsFragment().apply {
			arguments = Bundle().apply {
				putSerializable("Movie", movie)
			}
		}
	}


}