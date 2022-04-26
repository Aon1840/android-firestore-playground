package com.bearman.firestore_playground

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bearman.firestore_playground.adapter.UsersAdapter
import com.bearman.firestore_playground.interfaces.UserClickListener
import com.bearman.firestore_playground.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllUserActivity : AppCompatActivity(), UserClickListener {

    private lateinit var rvList: RecyclerView
    private lateinit var db: FirebaseFirestore
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_user)
        rvList = findViewById(R.id.rvList)
        setAdapterUserList()

        db = Firebase.firestore
        getUserData()
    }

    private fun getUserData() {
        val docRef = db.collection("users")
        docRef.addSnapshotListener { querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (querySnapshot != null) {
                userList.clear()
                for (document in querySnapshot.documents) {
                    document.toObject(User::class.java)?.let { userList.add(it) }
                }
                usersAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setAdapterUserList() {
        userList = arrayListOf()
        usersAdapter = UsersAdapter(userList, this, this)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = usersAdapter
    }

    override fun onEditClickListener(first: String) {
        Toast.makeText(this@AllUserActivity, "Edit! $first", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClickListener(first: String) {
        Toast.makeText(this@AllUserActivity, "Delete! $first", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun createIntent(
            context: Context,
        ): Intent {
            return Intent(context, AllUserActivity::class.java)
        }
    }
}