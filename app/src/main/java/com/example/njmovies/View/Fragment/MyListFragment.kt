package com.example.njmovies.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.njmovies.Model.MovieResult
import com.example.njmovies.View.Activity.Home.HomeViewModel
import com.example.njmovies.View.MyListAdapter
import com.example.njmovies.databinding.FragmentMyListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyListFragment : Fragment() {

	private val viewModel: HomeViewModel by activityViewModels()
	private lateinit var adapter: MyListAdapter
	private lateinit var binding: FragmentMyListBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentMyListBinding.inflate(inflater, container, false)

		adapter = MyListAdapter()

		binding.myListRecyclerView.adapter = adapter

		viewModel.getMovieList().observe(viewLifecycleOwner) { movieList ->
			for (movieId in movieList) {
				viewModel.getMovieById(movieId).enqueue(object : Callback<MovieResult> {
					override fun onResponse(
						call: Call<MovieResult>,
						response: Response<MovieResult>
					) {
						if (response.isSuccessful) {
							if (response.body() != null) {
								val movieResult = response.body()!!

								adapter.addMovie(movieResult)
							}
						}
					}

					override fun onFailure(call: Call<MovieResult>, t: Throwable) {
						t.printStackTrace()
					}
				})
			}
		}

		return binding.root
	}

	companion object {
		@JvmStatic
		fun newInstance() = MyListFragment()
	}

}