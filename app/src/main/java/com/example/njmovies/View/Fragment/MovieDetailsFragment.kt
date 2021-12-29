package com.example.njmovies.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.View.Activities.Home.HomeViewModel
import com.example.njmovies.databinding.FragmentMovieDetailsBinding
import com.example.njmovies.util.ResultListener

class MovieDetailsFragment : Fragment() {

	private val viewModel: HomeViewModel by activityViewModels()
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

			binding.addToMyListButton.setOnClickListener {
				viewModel.addMovieToList(movie.id, object : ResultListener {
					override fun onSuccess() {
						Toast.makeText(activity, "Movie ${movie.id} has been added to your list", Toast.LENGTH_SHORT).show()
					}

					override fun onFailure(error: Throwable) {
						Toast.makeText(activity, "Failed to add movie to your list", Toast.LENGTH_SHORT).show()
					}

				})
			}
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