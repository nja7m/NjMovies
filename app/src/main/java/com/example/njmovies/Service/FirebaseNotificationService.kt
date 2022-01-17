package com.example.njmovies.Service

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "FirebaseNotificationSer"

class FirebaseNotificationService : FirebaseMessagingService() {

	private var broadcaster: LocalBroadcastManager? = null

	override fun onCreate() {
		super.onCreate()
		broadcaster = LocalBroadcastManager.getInstance(this)
	}

	override fun onMessageReceived(remoteMessage: RemoteMessage) {
		super.onMessageReceived(remoteMessage)
		handleMessage(remoteMessage)
	}

	private fun handleMessage(remoteMessage: RemoteMessage) {
		val handler = Handler(Looper.getMainLooper())

		handler.post {
			remoteMessage.notification?.let {

				Toast.makeText(baseContext, "${it.title}: ${it.body}", Toast.LENGTH_SHORT).show()

			}
		}
	}
}