package com.example.njmovies.View.Activity.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.njmovies.Model.User
import com.example.njmovies.View.Activity.Home.HomeActivity
import com.example.njmovies.View.Activity.Login.LoginActivity
import com.example.njmovies.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		var binding = ActivityRegisterBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val registerViewModel: RegisterViewModel by viewModels()
		binding.textViewLogin.setOnClickListener {
			var intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
			finish()
		}

		binding.buttonRegister.setOnClickListener {
			var email = binding.emailEditText.text.toString()
			var password = binding.passwordEditText.text.toString()
			var name = binding.nameEditText.text.toString()
			var user = User(name, email, password)
			registerViewModel.register(user).observe(this) {
				if (it != null) {
					var intent = Intent(this, HomeActivity::class.java)
					startActivity(intent)
					finish()
					Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
				} else {
					Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
				}
			}
		}

	}
}