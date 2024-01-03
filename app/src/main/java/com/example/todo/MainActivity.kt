package com.example.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mf = supportFragmentManager
        val ft = mf.beginTransaction()

        ft.add(R.id.container, HomeFragment())
        ft.commit()
    }
}