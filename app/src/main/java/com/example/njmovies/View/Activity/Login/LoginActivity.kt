package com.example.njmovies.View.Activity.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.njmovies.Model.User
import com.example.njmovies.View.Activity.Home.HomeActivity
import com.example.njmovies.View.Activity.Register.RegisterActivity
import com.example.njmovies.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val loginViewModel: LoginViewModel by viewModels()
		binding.textViewRegister.setOnClickListener {
			var intent = Intent(this, RegisterActivity::class.java)
			startActivity(intent)
			finish()
		}
		val sp = getSharedPreferences("Nj", Context.MODE_PRIVATE)
		val editor = sp.edit()

		binding.buttonEmailLogin.setOnClickListener {
			var email = binding.emailEditText.text.toString()
			var password = binding.passwordEditText.text.toString()
			var user = User("", email, password)

			loginViewModel.Login(user).observe(this) {
				if (it != null) {
					editor.putString("email", email)
					editor.putString("password", password)
					editor.commit()
					var intent = Intent(this, HomeActivity::class.java)
					startActivity(intent)
					finish()
				} else {
					Toast.makeText(this, "Failed to Login", Toast.LENGTH_SHORT).show()
				}
			}
		}

	}
}