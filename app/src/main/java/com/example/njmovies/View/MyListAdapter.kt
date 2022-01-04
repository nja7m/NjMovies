package com.example.njmovies.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.R
import com.example.njmovies.View.Fragment.MovieDetailsFragment
import com.squareup.picasso.Picasso

class MyListAdapter(private var list: MutableList<MovieResult> = mutableListOf()) :
	RecyclerView.Adapter<MyListViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
		var v = LayoutInflater.from(parent.context).inflate(R.layout.home_movie_card_view, parent, false)
		return MyListViewHolder(v)
	}

	override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
		Picasso
			.get()
			.load("https://image.tmdb.org/t/p/w500/" + list[position].poster_path)
			.fit()
			.centerCrop()
			.into(holder.poster)

		holder.itemView.setOnClickListener {
			val activity = it.context as AppCompatActivity
			activity.supportFragmentManager.beginTransaction()
				.replace(
					R.id.home_fragment_container_view,
					MovieDetailsFragment.newInstance(list[position])
				)
				.commit()
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