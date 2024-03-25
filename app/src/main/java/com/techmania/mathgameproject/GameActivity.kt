package com.techmania.mathgameproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textViewSC : TextView
    lateinit var textViewLF : TextView
    lateinit var textViewTM : TextView

    lateinit var textViewTime : TextView
    lateinit var textViewScore : TextView
    lateinit var textViewLife : TextView

    lateinit var textViewQuest : TextView
    lateinit var editTextAns : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 30000
    var timeLeftInMillis : Long = startTimerInMillis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title="Addition"

        textViewSC = findViewById(R.id.textView1)
        textViewLF = findViewById(R.id.textView2)
        textViewTM = findViewById(R.id.textView3)

        textViewTime =findViewById(R.id.textViewTime)
        textViewScore =findViewById(R.id.textViewScore)
        textViewLife =findViewById(R.id.textViewLife)


        textViewQuest = findViewById(R.id.textViewQuest)
        editTextAns = findViewById(R.id.editTextTextAns)

        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {
            val input = editTextAns.text.toString()
            if (input == ""){
                Toast.makeText(applicationContext,"WRITE AN ANSWER",Toast.LENGTH_LONG).show()
            }
            else{
                pauseTimer()
                val userAnswer = input.toInt()
                if(userAnswer==correctAnswer){
                    userScore += 10
                    textViewQuest.text="Answer is correct"
                    textViewScore.text=userScore.toString()

                }
                else{
                    userLife--
                    textViewQuest.text="Answer is wrong"
                    textViewLife.text=userLife.toString()
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue()
            editTextAns.setText("")

            if (userLife==0){
                Toast.makeText(applicationContext,"game over",Toast.LENGTH_LONG).show()
                val intent =Intent(this@GameActivity,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }
            else{
                gameContinue()
            }
        }



    }

    fun gameContinue(){
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)
        textViewQuest.text = "$number1 + $number2"
        correctAnswer = number1 + number2
        startTimer()
    }

    fun startTimer(){
        timer=object : CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinsihed: Long) {
                timeLeftInMillis=millisUntilFinsihed
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                updateText()
                resetTimer()

                userLife--
                textViewLF.text=userLife.toString()
                textViewQuest.text="Time Is Up!"
            }

        }.start()
    }
    fun updateText(){
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        textViewTime.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer(){
        timeLeftInMillis=startTimerInMillis
        updateText()
    }
}