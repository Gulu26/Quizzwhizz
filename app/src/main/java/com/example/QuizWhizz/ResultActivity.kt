package com.example.QuizWhizz

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.QuizWhizz.activities.models.Question
import com.example.QuizWhizz.activities.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    lateinit var txtAnswer: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        setUpViews()

    }

    private fun setUpViews() {
        val quizData= intent.getStringExtra("QUIZ")
        quiz =Gson().fromJson<Quiz>(quizData, Quiz::class.java)//here deoendancy used for deserialable the data
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        //here code in html and make srting
        val builder = StringBuilder("")
        for(entry :MutableMap.MutableEntry<String, Question> in quiz.question.entries){
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font colo = '#009688'>Answer: ${question.Answer}</font><br/><br/>")
       }
// here we set the code html to set in the view
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT)
        }else{
            txtAnswer.text = Html.fromHtml(builder.toString());
        }


    }

    private fun calculateScore() {
        var score = 0
        for(entry :MutableMap.MutableEntry<String, Question> in quiz.question.entries){
            val question = entry.value


        }
    }
}