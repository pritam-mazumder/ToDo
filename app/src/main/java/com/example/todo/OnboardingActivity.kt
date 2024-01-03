package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todo.databinding.ActivityOnboadingBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class OnboardingActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityOnboadingBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private var currentFragmentIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = Firebase.auth

        showFragment(Onboarding1Fragment())

        binding.next.setOnClickListener {
            when (currentFragmentIndex) {
                1 -> {
                    showFragment(Onboarding2Fragment())
                    currentFragmentIndex = 2
                }

                2 -> {
                    binding.next.text = "Get Started"
                    showFragment(Onboarding3Fragment())
                    currentFragmentIndex = 3
                }

                3 -> {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()
                }
            }
        }

        binding.skip.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }

    private fun showFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()
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

