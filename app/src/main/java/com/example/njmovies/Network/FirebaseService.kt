package com.example.njmovies.Network

import androidx.lifecycle.MutableLiveData
import com.example.njmovies.util.ResultListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class FirebaseService() {

	private val firestore = FirebaseFirestore.getInstance()

	companion object {
		var firebaseAuthService: FirebaseAuth? = null
			private set
			get() {
				if (field == null) {
					field = Firebase.auth
				}

				return field
			}
	}

	fun addMovieToList(id: Int, resultListener: ResultListener) {
		firebaseAuthService!!.uid?.let { userUid ->
			firestore
				.collection("users")
					.document(userUid)
					.update("list", FieldValue.arrayUnion(id))
				.addOnSuccessListener {
					resultListener.onSuccess()
				}
				.addOnFailureListener { exception ->
					resultListener.onFailure(exception)
					exception.printStackTrace()
				}
		}
	}

	fun getMovieList(): MutableLiveData<List<Int>> {
		val liveData = MutableLiveData<List<Int>>()

		firebaseAuthService!!.uid?.let { userUid ->
			firestore
				.collection("users")
				.document(userUid)
				.get()
				.addOnSuccessListener { document ->
					val list = document["list"] as List<Int>
					liveData.postValue(list)
				}
				.addOnFailureListener { exception ->
					liveData.postValue(null)
					exception.printStackTrace()
				}
		}

		return liveData
	}
}