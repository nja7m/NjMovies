package com.example.njmovies.View.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.njmovies.R
import com.example.njmovies.View.Activity.Home.HomeViewModel
import com.example.njmovies.View.Activity.Login.LoginActivity


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
		var contactSupport = v.findViewById<TextView>(R.id.here)
		contactSupport.setOnClickListener {
			val intent = Intent(Intent.ACTION_SENDTO)
			intent.data = Uri.parse("mailto:")
			intent.putExtra(Intent.EXTRA_EMAIL, "njah.rashid@outlook.com")
			intent.putExtra(Intent.EXTRA_SUBJECT, "Customer Service")
			intent.putExtra(Intent.EXTRA_TEXT, " ")
			startActivity(intent)

		}


		return v

	}

	companion object {
		@JvmStatic
		fun newInstance() = SettingFragment()
	}


}