package com.example.njmovies.Network

import androidx.lifecycle.MutableLiveData
import com.example.njmovies.util.ResultListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class FirebaseService {

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

	fun addMovieToList(id: Long, resultListener: ResultListener) {
		firebaseAuthService!!.uid?.let { userUid ->
			firestore
				.collection("users")
				.document(userUid)
				.update("movieList", FieldValue.arrayUnion(id))
				.addOnSuccessListener {
					resultListener.onSuccess()
				}
				.addOnFailureListener { exception ->
					resultListener.onFailure(exception)
					exception.printStackTrace()
				}
		}
	}

	fun deleteMovieFromList(id: Long, resultListener: ResultListener) {
		firebaseAuthService!!.uid?.let { userUid ->
			firestore
				.collection("users")
				.document(userUid)
				.update("movieList", FieldValue.arrayRemove(id))
				.addOnSuccessListener {
					resultListener.onSuccess()
				}
				.addOnFailureListener {
					resultListener.onFailure(it)
					it.printStackTrace()
				}

		}
	}

	fun getMovieList(): MutableLiveData<List<Long>> {
		val liveData = MutableLiveData<List<Long>>()

		firebaseAuthService!!.uid?.let { userUid ->
			firestore
				.collection("users")
				.document(userUid)
				.get()
				.addOnSuccessListener { document ->
					val list = document["movieList"] as List<Long>
					liveData.postValue(list)
				}
				.addOnFailureListener { exception ->
					liveData.postValue(null)
					exception.printStackTrace()
				}
		}

		return liveData
	}

	fun movieISExist(id: Long): MutableLiveData<Boolean> {
		var liveDate = MutableLiveData<Boolean>()

		getMovieList().observeForever {
			println("" + id + "    " + it)
			liveDate.postValue(it.contains(id))
		}
		return liveDate
	}
}