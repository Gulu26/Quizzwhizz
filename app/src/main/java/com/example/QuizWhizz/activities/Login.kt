package com.example.QuizWhizz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.QuizWhizz.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var etEmailAddress: EditText
    lateinit var etPassword: EditText
    lateinit var btnsignup: Button
    lateinit var txtlogin3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        etEmailAddress = findViewById(R.id.etEmailAddress)
        etPassword = findViewById(R.id.etPassword)
        btnsignup = findViewById(R.id.btnsignup)
        txtlogin3 = findViewById(R.id.txtlogin3)

        btnsignup = findViewById(R.id.btnsignup)


        btnsignup.setOnClickListener {
            login()
        }


        txtlogin3.setOnClickListener{
            val intent = Intent( this, signin::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun login(){
        val email = etEmailAddress.text.toString()
        val password = etPassword.text.toString()

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, " Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, " Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}