package com.bearman.firestore_playground

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var btSeeMore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btSeeMore = findViewById(R.id.btSeeMore)

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


        btSeeMore.setOnClickListener {
            startActivity(AllUserActivity.createIntent(this@MainActivity))
        }
    }
}