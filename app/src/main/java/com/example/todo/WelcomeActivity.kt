package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.arrowLeft.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }

        binding.welcomeLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.welcomeRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}