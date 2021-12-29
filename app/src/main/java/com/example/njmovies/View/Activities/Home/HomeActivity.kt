package com.example.njmovies.View.Activities.Home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.njmovies.R
import com.example.njmovies.View.Activities.Login.LoginActivity
import com.example.njmovies.View.Fragment.HomeFragment
import com.example.njmovies.View.Fragment.MyListFragment
import com.example.njmovies.View.Fragment.SearchFragment
import com.example.njmovies.View.Fragment.SettingFragment
import com.example.njmovies.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
	var drawerLayout: DrawerLayout? = null
	var actionBarDrawerToggle: ActionBarDrawerToggle? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		if (FirebaseAuth.getInstance().currentUser == null) {
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
			finish()
			return
		}

		val viewModel: HomeViewModel by viewModels()
		var binding = ActivityHomeBinding.inflate(layoutInflater)
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

	}

	private fun replaceFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(R.id.home_fragment_container_view, fragment).commit()
	}
}