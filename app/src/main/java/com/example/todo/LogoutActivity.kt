package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityLogoutBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LogoutActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLogoutBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            Toast.makeText(this, "User logged out successfuly", Toast.LENGTH_SHORT).show()
        }
    }
}