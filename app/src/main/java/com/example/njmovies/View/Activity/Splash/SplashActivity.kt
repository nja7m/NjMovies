package com.example.njmovies.View.Activity.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.njmovies.R
import com.example.njmovies.View.Activity.Home.HomeActivity
import com.example.njmovies.View.Activity.Login.LoginActivity


class SplashActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		Handler(Looper.getMainLooper()).postDelayed({
			val preferences = getSharedPreferences("Nj", MODE_PRIVATE)
			val email = preferences.getString("email", "")
			val password = preferences.getString("password", "")
			Log.i("nj", "password: $password\t\temail: $email")

			if (password != null && email != null && email.isNotEmpty() && password.isNotEmpty()) {
				var intent = Intent(this, HomeActivity::class.java)
				startActivity(intent)
				finish()
			} else {
				var intent = Intent(this, LoginActivity::class.java)
				startActivity(intent)
				finish()
			}

		}, 1000)
	}
}