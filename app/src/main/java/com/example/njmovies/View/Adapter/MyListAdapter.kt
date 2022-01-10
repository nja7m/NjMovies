package com.example.njmovies.View

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.View.Activity.MovieDetails.MovieDetailsActivity
import com.squareup.picasso.Picasso

class MyListAdapter(private var list: MutableList<MovieResult> = mutableListOf()) :
	RecyclerView.Adapter<MyListViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
		var v = LayoutInflater.from(parent.context)
			.inflate(R.layout.home_movie_card_view, parent, false)
		return MyListViewHolder(v)
	}

	override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
		val context = holder.itemView.context
		Picasso
			.get()
			.load("https://image.tmdb.org/t/p/w500/" + list[position].poster_path)
			.fit()
			.centerCrop()
			.into(holder.poster)

		holder.itemView.setOnClickListener {

			val intent = Intent(context, MovieDetailsActivity::class.java)
			intent.putExtra("Movie", list[position])
			context.startActivity(intent)
		}
	}

	override fun getItemCount(): Int {
		return list.size
	}

	fun addMovie(movie: MovieResult) {
		list.add(movie)
		notifyItemInserted(list.size - 1)
	}

}

class MyListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	var poster = itemView.findViewById<ImageView>(R.id.item_movie_poster)

}