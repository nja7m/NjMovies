package com.example.njmovies.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.example.njmovies.View.Activity.Home.HomeViewModel
import com.example.njmovies.View.Adapter.SearchAdapter
import com.example.njmovies.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

	private lateinit var binding: FragmentSearchBinding
	private val viewModel: HomeViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

		binding.searchRecyclerView.adapter = SearchAdapter()

		binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				return false
			}

			//			will do search and bring data and submit
			override fun onQueryTextChange(newText: String?): Boolean {
				viewModel.viewModelScope.launch {
					viewModel.searchMovies(newText).distinctUntilChanged()
						.collectLatest { pagedData ->
							(binding.searchRecyclerView.adapter as SearchAdapter).submitData(
								pagedData
							)
						}
				}
				return false
			}

		})

		return binding.root
	}

	companion object {
		@JvmStatic
		fun newInstance() = SearchFragment()
	}
}