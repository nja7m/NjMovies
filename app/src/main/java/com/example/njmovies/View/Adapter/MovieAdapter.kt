package com.example.njmovies.View.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.View.Activity.MovieDetails.MovieDetailsActivity
import com.squareup.picasso.Picasso

class MovieAdapter :
	PagingDataAdapter<MovieResult, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return MovieViewHolder.getInstance(parent)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as? MovieViewHolder)?.bind(item = getItem(position))
		val context = holder.itemView.context
		holder.itemView.setOnClickListener {
			val intent = Intent(context, MovieDetailsActivity::class.java)
			intent.putExtra("Movie", getItem(position))
			context.startActivity(intent)

		}
	}


	class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		companion object {
			//get instance of the DoggoImageViewHolder
			fun getInstance(parent: ViewGroup): MovieViewHolder {
				val inflater = LayoutInflater.from(parent.context)
				val view = inflater.inflate(R.layout.home_movie_card_view, parent, false)
				return MovieViewHolder(view)
			}
		}

		var moviePoster: ImageView = view.findViewById(R.id.item_movie_poster)

		fun bind(item: MovieResult?) {
			//loads image from network using coil extension function
			item?.let { movieResult ->
				if (movieResult.poster_path.isEmpty()) {
					return@let
				}

				Picasso
					.get()
					.load("https://image.tmdb.org/t/p/w500/" + movieResult.poster_path)
					.fit()
					.centerCrop()
					.into(moviePoster)
			}
		}

	}

	companion object {
		private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<MovieResult>() {
			override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
				oldItem == newItem

			override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
				oldItem == newItem
		}
	}

}
