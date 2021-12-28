package com.example.njmovies.Network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseService {

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
}