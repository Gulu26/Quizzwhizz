package com.example.QuizWhizz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizWhizz.R
import com.example.QuizWhizz.ResultActivity
import com.example.QuizWhizz.activities.adapters.OptionAdapter
import com.example.QuizWhizz.activities.models.Question
import com.example.QuizWhizz.activities.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    lateinit var optionList: RecyclerView
    lateinit var description: TextView
    lateinit var btnNext: Button
    lateinit var btnPrevious: Button

    lateinit var btnSubmit: Button
    var quizzes : MutableList<Quiz>?= null
    var question : MutableMap<String, Question>?= null
    var index = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        description = findViewById(R.id.description)
        optionList = findViewById(R.id.optionList)
        btnPrevious = findViewById(R.id.btnPrevious)

        btnNext = findViewById(R.id.btnNext)
        btnSubmit = findViewById(R.id.btnSubmit)

        setUpFirestore()
        setUpEventListener()

    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener {
            index--
            bindViews()
        }
        btnNext.setOnClickListener {
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener {
            Log.d("FINALQUIZ", question.toString())


            val intent= Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)


        }


    }



    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if(date!= null){
        firestore.collection("quizzes").whereEqualTo("Title", date)
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty){
                    quizzes= it.toObjects(Quiz::class.java)
                    question = quizzes!![0].question
                    bindViews()
            }


            }
        }
    }

    private fun bindViews() {
        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        btnNext.visibility = View.GONE
        if(index == 1){
            btnNext.visibility = View.VISIBLE
        }
        else if(index == question!!.size){
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        else{
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }
        val question = question!!["question$index"]
        question?.let {
            description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter= optionAdapter
            optionList.setHasFixedSize(true) }

       
    }


}