package com.example.dinnerdecider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val foods = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foods.add("Hamburger")
        foods.add("Pizza")
        foods.add("Mexican")
        foods.add("Mexican")
        foods.add("Chinese")

        val decide: Button = findViewById(R.id.decide)
        val addfood: TextView = findViewById(R.id.addfood)
        decide.setOnClickListener {
            addfood.text = foods[rand(0, foods.size)]
        }

        addfood.text = foods[0]
    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    fun addfood(v: View){
        val newfood : EditText = findViewById(R.id.newfoodentry)
        foods.add(newfood.text.toString())
    }
}