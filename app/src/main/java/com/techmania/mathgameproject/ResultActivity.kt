package com.techmania.mathgameproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var textViewResult: TextView
    lateinit var buttonPA: Button
    lateinit var buttonExit: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textViewResult = findViewById(R.id.textViewResult)
        buttonPA = findViewById(R.id.buttonPA)
        buttonExit = findViewById(R.id.buttonExit)
        val score = intent.getIntExtra("score",0)
        textViewResult.text= " your score $score"

        buttonPA.setOnClickListener {
            val intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }






    }
}