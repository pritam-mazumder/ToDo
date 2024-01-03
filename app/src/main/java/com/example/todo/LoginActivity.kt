package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.arrowLeft.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }

        binding.lregister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.login.setOnClickListener {

            val email = binding.lEmail.text.toString()
            val password = binding.lPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Incorrect username or password: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    //  Check current auth state
    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}