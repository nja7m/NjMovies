package com.example.njmovies.View.Home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.View.Fragment.MovieDetailsFragment
import com.squareup.picasso.Picasso

class MovieAdapter :
	PagingDataAdapter<MovieResult, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as? MovieViewHolder)?.bind(item = getItem(position))
		holder.itemView.setOnClickListener {
			val activity = it.context as AppCompatActivity
			val movieDetailsFragment = MovieDetailsFragment()
			activity.supportFragmentManager.beginTransaction()
				.replace(R.id.home_fragment_container_view, MovieDetailsFragment.newInstance(getItem(position)!!))
				.commit()
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return MovieViewHolder.getInstance(parent)
	}

	class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		companion object {
			//get instance of the DoggoImageViewHolder
			fun getInstance(parent: ViewGroup): MovieViewHolder {
				val inflater = LayoutInflater.from(parent.context)
				val view = inflater.inflate(R.layout.movie_card_view, parent, false)
				return MovieViewHolder(view)
			}
		}

		var moviePoster: ImageView = view.findViewById(R.id.item_movie_poster)

		fun bind(item: MovieResult?) {
			//loads image from network using coil extension function
			item?.let { movieResult ->
				if (movieResult.poster_path.isEmpty()) {
					Log.e("MovieAdapter", "bind: Poster path is empty for ${movieResult.id}")
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

//class MovieAdapter(private var list: MutableList<MovieResult> = mutableListOf()) :
//	PagingDataAdapter<MovieResult, MovieViewHolder>(MovieComparator) {
////	init {
////		setHasStableIds(true)
////	}
//
//	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//		var v = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_view, parent, false)
//		Log.d("MovieAdapter", "onCreateViewHolder: View inflated")
//		return MovieViewHolder(v)
//	}
//
//	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
////		holder.movieTitle.text = list[position].title
////		holder.releaseDate.text = list[position].release_date
//		val item = getItem(position)
//		if (item != null) {
//			Log.d("MovieAdapter", "onBindViewHolder: Non null")
//			if (item.poster_path.isNotEmpty()) {
//				Picasso
//					.get()
//					.load("https://image.tmdb.org/t/p/w500/" + list[position].poster_path)
//					.fit()
//					.centerCrop()
//					.into(holder.poster)
//			}
//		} else {
//			Log.d("MovieAdapter", "onBindViewHolder: Null item")
//		}
//		holder.itemView.setOnClickListener {
//			var intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
//			intent.putExtra("movie", list[position])
//			holder.itemView.context.startActivity(intent)
//		}
//	}
//
////	override fun getItemId(position: Int): Long {
////		return position.toLong()
////	}
////
////	override fun getItemViewType(position: Int): Int {
////		return position
////	}
//
////	fun updateMovies(movies: PagingData<MovieResult>) {
////		list = movies
////		notifyDataSetChanged()
////	}
//
//	fun appendMovies(movies: List<MovieResult>) {
//		list.clear()
//		list.addAll(movies)
//		notifyItemRangeInserted(
//			list.size,
//			movies.size - 1
//		)
//	}
//
//	override fun getItemCount(): Int {
//		return list.size
//	}
//
//	object MovieComparator: DiffUtil.ItemCallback<MovieResult>() {
//		override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
//			return oldItem.id == newItem.id
//		}
//
//		override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
//			return oldItem == newItem
//		}
//	}
//}
//
//class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//	//	var movieTitle = itemView.findViewById<TextView>(R.id.)
////	var releaseDate = itemView.findViewById<TextView>(R.id.releaseDateTextView)
//	var poster = itemView.findViewById<ImageView>(R.id.item_movie_poster)
//}