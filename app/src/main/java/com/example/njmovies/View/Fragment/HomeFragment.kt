package com.example.njmovies.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.njmovies.Model.MovieCategory
import com.example.njmovies.View.Activity.Home.HomeViewModel
import com.example.njmovies.View.Adapter.MovieAdapter
import com.example.njmovies.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel: HomeViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)

		val movieCategoriesMap = mapOf(
			MovieCategory.POPULAR to binding.popularMovies,
			MovieCategory.NOW_PLAYING to binding.nowPlayingMovies,
			MovieCategory.TOP_RATED to binding.topRatedMovies,
			MovieCategory.UPCOMING to binding.upComingMovies
		)

		for ((movieCategory, recyclerView) in movieCategoriesMap) {
			recyclerView.apply {
				layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
				adapter = MovieAdapter()
			}

			viewModel.viewModelScope.launch {
				viewModel.getMovies(movieCategory).distinctUntilChanged()
					.collectLatest { pagedData ->
						(recyclerView.adapter as MovieAdapter).submitData(pagedData)
					}
			}
		}


		return binding.root
	}

	companion object {
		@JvmStatic
		fun newInstance() = HomeFragment()
	}

}