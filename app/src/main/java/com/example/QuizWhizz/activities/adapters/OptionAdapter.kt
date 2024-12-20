package com.example.QuizWhizz.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.QuizWhizz.R
import com.example.QuizWhizz.activities.models.Question

class OptionAdapter (val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.OptionViewholder>(){

        private var options: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)

    inner class OptionViewholder(itemView: View): RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option1)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewholder {

       val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewholder(view)

    }
    override fun onBindViewHolder(holder: OptionViewholder, position: Int) {
        holder.optionView.text = options[position]

        holder.itemView.setOnClickListener {
            question.userAnswer  = options[position]
            notifyDataSetChanged()

        }
        if(question.userAnswer == options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selectionbg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }
    override fun getItemCount(): Int {
        return options.size
    }

}