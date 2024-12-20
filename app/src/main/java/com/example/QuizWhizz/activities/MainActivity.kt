package com.example.QuizWhizz.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizWhizz.ProfileActivity
import com.example.QuizWhizz.R
import com.example.QuizWhizz.activities.adapters.QuizAdapter
import com.example.QuizWhizz.activities.models.Quiz
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {
    lateinit var appBar: MaterialToolbar
    lateinit var mainDrawer: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    lateinit var quizRecyclerView: RecyclerView
    lateinit var nav: NavigationView

    lateinit var btnDatePicker: FloatingActionButton
    private var QuizList = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBar = findViewById(R.id.appBar)
        mainDrawer = findViewById(R.id.mainDrawer)
        quizRecyclerView = findViewById(R.id.quizRecyclerView)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        nav = findViewById(R.id.nav)

        populateDummyData()
        setUpViews()
    }


    private fun populateDummyData(){
        QuizList.add(Quiz("1.09.2024", "1.09.2024"))
        QuizList.add(Quiz("2.09.2024", "2.09.2024"))
        QuizList.add(Quiz("3.09.2024", "3.09.2024"))



    }

    fun setUpViews(){
        setUpDrawerLayout()
        setUpFireStore()
        setUpRecyclerView()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        btnDatePicker.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DataPicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)

            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER"," Date Picker Cancelled" )
            }
        }
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if( value == null || error != null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            QuizList.clear()
            QuizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }

    }

    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this, QuizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        quizRecyclerView.adapter = adapter
    }


    fun setUpDrawerLayout(){
       setSupportActionBar(appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
        nav.setNavigationItemSelectedListener {
            val intent = Intent(this,ProfileActivity::class.java )
            startActivity(intent)
            mainDrawer.closeDrawers()
            true// we implememnt to to show that click eventall handel aRE profomend no need to performed any other handels
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}







