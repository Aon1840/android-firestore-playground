package com.bearman.firestore_playground

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var btSeeMore: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btSeeMore = findViewById(R.id.btSeeMore)
        textView = findViewById(R.id.textView)

        val db = Firebase.firestore

        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada"+ (0..100).random().toString(),
            "last" to "Lovelace",
            "born" to 1815
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

//        // get 1 time
//        val docRefGet = db.collection("BENZ").document("ldzUpM6jpO3zABXwSXzX")
//        docRefGet.get()
//            .addOnSuccessListener { result ->
//                Log.d(TAG, "Current data get: ${result.data}")
//            }
//            .addOnFailureListener { e ->
//                Log.d(TAG, "Current data get: null")
//            }
//
//        // get first time and subscribe data when change
//        val docRef = db.collection("BENZ").document("ldzUpM6jpO3zABXwSXzX")
//        docRef.addSnapshotListener { snapshot, e ->
//            if (e != null) {
//                Log.w(TAG, "Listen failed.", e)
//                return@addSnapshotListener
//            }
//
//            if (snapshot != null && snapshot.exists()) {
//                Log.d(TAG, "Current data: ${snapshot.data}")
//                textView.text = snapshot.data.toString()
//            } else {
//                Log.d(TAG, "Current data: null")
//            }
//        }

        btSeeMore.setOnClickListener {
            startActivity(AllUserActivity.createIntent(this@MainActivity))
        }
    }
}