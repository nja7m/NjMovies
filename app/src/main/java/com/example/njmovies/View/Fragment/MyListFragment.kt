package com.example.njmovies.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.njmovies.R
import com.example.njmovies.View.Activities.Home.HomeViewModel
import com.example.njmovies.databinding.FragmentMyListBinding


class MyListFragment : Fragment() {

	private val viewModel: HomeViewModel by activityViewModels()
	private lateinit var binding: FragmentMyListBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentMyListBinding.inflate(inflater, container, false)

		return binding.root
	}

	companion object {
		@JvmStatic
		fun newInstance() = MyListFragment()
	}

}