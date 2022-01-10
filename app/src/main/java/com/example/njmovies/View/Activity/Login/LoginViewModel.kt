package com.example.njmovies.View.Activity.Login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.njmovies.Model.User
import com.example.njmovies.Network.FirebaseService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
	val firebaseAuthService = FirebaseService.firebaseAuthService!!
	val db = Firebase.firestore

	fun Login(user: User): MutableLiveData<User> {
		var liveData = MutableLiveData<User>()
		firebaseAuthService.signInWithEmailAndPassword(user.email, user.password)
			.addOnSuccessListener {
				liveData.postValue(user)
			}.addOnFailureListener {
				liveData.postValue(null)
				it.message?.let { it1 -> Log.e("Nj", it1) }
				Log.e("Nj", user.toString())
			}
		return liveData
	}

}