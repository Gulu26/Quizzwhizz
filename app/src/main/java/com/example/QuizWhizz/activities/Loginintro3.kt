package com.example.QuizWhizz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.QuizWhizz.R
import com.google.firebase.auth.FirebaseAuth

class loginintro3 : AppCompatActivity() {
    lateinit var txt1: TextView
    lateinit var txt2: TextView
    lateinit var btnget: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginintro3)
        txt1 = findViewById(R.id.txt1)
        txt2 = findViewById(R.id.txt2)
        val auth= FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            Toast.makeText(this, "User is already logged in!", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }
        btnget = findViewById(R.id.btnget)

        btnget.setOnClickListener {
            redirect("LOGIN")
        }
    }


    private fun redirect(name: String){
        val intent  = when(name){
            "LOGIN" -> Intent(this, Login::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}