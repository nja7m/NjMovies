package com.example.njmovies.View.Activity.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.njmovies.R
import com.example.njmovies.View.Activity.Login.LoginActivity
import com.example.njmovies.View.Fragment.HomeFragment
import com.example.njmovies.View.Fragment.MyListFragment
import com.example.njmovies.View.Fragment.SearchFragment
import com.example.njmovies.View.Fragment.SettingFragment
import com.example.njmovies.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

private const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (FirebaseAuth.getInstance().currentUser == null) {
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
			finish()
			return
		}

		val viewModel: HomeViewModel by viewModels()
		val firebaseMessaging = FirebaseMessaging.getInstance()
		var binding = ActivityHomeBinding.inflate(layoutInflater)
		setSupportActionBar(binding.toolbar)
		setContentView(binding.root)
		binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_baseline_home_24)
		binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_search_24)
		binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_check_24)
		binding.tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_settings_24)

		binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
			override fun onTabSelected(tab: TabLayout.Tab?) {
				when (tab?.position) {
					0 -> replaceFragment(HomeFragment.newInstance())
					1 -> replaceFragment(SearchFragment.newInstance())
					2 -> replaceFragment(MyListFragment.newInstance())
					3 -> replaceFragment(SettingFragment.newInstance())
				}
			}

			override fun onTabUnselected(tab: TabLayout.Tab?) {}

			override fun onTabReselected(tab: TabLayout.Tab?) {
				if (tab?.position == 0) {
					replaceFragment(HomeFragment.newInstance())
				}
			}

		})

		firebaseMessaging.token.addOnCompleteListener { tokenTask ->
			if (!tokenTask.isSuccessful) {
				Log.w(TAG, "Fetching FCM registration token failed", tokenTask.exception)
				return@addOnCompleteListener
			}

			val token = tokenTask.result
			Log.i(TAG, "onCreate: Notifications token ($token)")
//			Toast.makeText(baseContext, "Notification token: $token", Toast.LENGTH_SHORT).show()
		}


	}

	private fun replaceFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(R.id.home_fragment_container_view, fragment).commit()
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.home_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}
}