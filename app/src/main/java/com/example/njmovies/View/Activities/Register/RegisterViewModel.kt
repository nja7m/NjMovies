package com.example.njmovies.View.Activities.Register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.njmovies.Model.User
import com.example.njmovies.Network.FirebaseService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {

	val firebaseAuthService = FirebaseService.firebaseAuthService!!
	val db = Firebase.firestore

	fun register(user: User): MutableLiveData<User> {
		var liveData = MutableLiveData<User>()
		firebaseAuthService
			.createUserWithEmailAndPassword(user.email, user.password)
			.addOnSuccessListener {
				db.collection("users").document(firebaseAuthService.currentUser!!.uid).set(user)
				liveData.postValue(user)
			}
			.addOnFailureListener {
				liveData.postValue(null)
				Log.e("nj", "Error: ${it.message}")
			}

		return liveData

	}
}