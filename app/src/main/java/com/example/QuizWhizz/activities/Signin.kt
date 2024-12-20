package com.example.QuizWhizz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.QuizWhizz.R
import com.google.firebase.auth.FirebaseAuth

class signin : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var etEmailAddress: EditText
    lateinit var etPassword: EditText
    lateinit var etconfirmPassword: EditText
    lateinit var btnsignup: Button
    lateinit var txtlogin3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        firebaseAuth = FirebaseAuth.getInstance()

        etEmailAddress = findViewById<EditText>(R.id.etEmailAddress)
        etPassword = findViewById<EditText>(R.id.etPassword)
        etconfirmPassword = findViewById<EditText>(R.id.etconfirmPassword)
        txtlogin3 = findViewById(R.id.txtlogin3)

        btnsignup = findViewById<Button>(R.id.btnsignup)

        btnsignup.setOnClickListener {
            signupuser()
        }
        txtlogin3.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
    private fun signupuser(){
        val email = this.etEmailAddress.text.toString()
        val password = this.etPassword.text.toString()
        val confirmPassword = etconfirmPassword.text.toString()



        if(email.isBlank() || password.isBlank()|| confirmPassword.isBlank()){    //its a validation
            Toast.makeText(this,"Email and Password can't be blank", Toast.LENGTH_SHORT ).show()
            return
        }
        if (password != confirmPassword){
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("SignInActivity", "Attempting to create user with email: $email")


        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, " Login Successfull", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Error creating user: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    Log.e("SignInActivity", "Error: ${it.exception?.message}")
                }
            }

    }
}