package com.example.njmovies.util

import android.provider.ContactsContract
import java.util.regex.Pattern

class Validation {
	private val regPassword = "^(?=.*[0-9])"+
			"(?=.*[a-z])"+
			"(?=.*[A-Z])"+
			"(?=.*[!@#\\$%\\^&\\*])"+
			"(?=\\S+$)"+
			".{8,}$"
	private val regEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"

	fun emailIsValid(email:String):Boolean{
		val pattern = Pattern.compile(regEmail)
		val matcher = pattern.matcher(email)
		return matcher.matches()
	}
	fun passwordIsValid(password:String):Boolean{
		val pattern = Pattern.compile(regPassword)
		val matcher = pattern.matcher(password)
		return matcher.matches()
	}
}