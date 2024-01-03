package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todo.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.arrowLeft.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }

        binding.rlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.register.setOnClickListener {
            val email = binding.rEmail.text.toString()
            val password = binding.rPassword.text.toString()
            val repeatPassword = binding.rrPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            } else if (password != repeatPassword) {
                Toast.makeText(this, "Both password must be same!", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, WelcomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Registration Failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}