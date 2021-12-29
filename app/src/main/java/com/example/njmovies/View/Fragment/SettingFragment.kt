package com.example.njmovies.View.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.njmovies.R
import com.example.njmovies.View.Activities.Home.HomeViewModel
import com.example.njmovies.View.Activities.Login.LoginActivity


class SettingFragment : Fragment() {
	//	private lateinit var binding: FragmentSettingBinding
	private val viewModel: HomeViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		var v = inflater.inflate(R.layout.fragment_setting, container, false)
		var logoutButton = v.findViewById<Button>(R.id.buttonLogout)
		logoutButton.setOnClickListener {
			viewModel.logout()
			var intent = Intent(activity, LoginActivity::class.java)
			startActivity(intent)


		}
		return v

		// Inflate the layout for this fragment
//		binding = FragmentSettingBinding.inflate(inflater,container,false)
//        binding.buttonLogout.setOnClickListener {
//        	viewModel.logout()
//	        requireActivity().run{
//		        startActivity(Intent(this, LoginActivity::class.java))
//		        finish()
//	        }
//        }
////	        Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show()
////	        val intent =Intent(this@SettingFragment.context,LoginActivity::class.java)
////	        startActivity(intent)
//
//
//		return binding.root
	}

	companion object {
		@JvmStatic
		fun newInstance() = SettingFragment()
	}

}