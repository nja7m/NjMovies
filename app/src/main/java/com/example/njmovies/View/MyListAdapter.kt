package com.example.njmovies.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.squareup.picasso.Picasso

class MyListAdapter(private var list: MutableList<MovieResult>) :
	RecyclerView.Adapter<MyListViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
		var v = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_view, parent, false)
		return MyListViewHolder(v)
	}

	override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
		Picasso
			.get()
			.load("https://image.tmdb.org/t/p/w500/" + list[position].poster_path)
			.fit()
			.centerCrop()
			.into(holder.poster)
	}

	override fun getItemCount(): Int {
		return list.size
	}

}

class MyListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	var poster = itemView.findViewById<ImageView>(R.id.item_movie_poster)

}