package com.example.myclicker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Game : AppCompatActivity(), View.OnClickListener {
    companion object {
        var currentScore = 0
        var clickPrice = 1

        val step : Int = 5
        var costImprove : Int = 10
        @SuppressLint("StaticFieldLeak")
        var totalSum: TextView? = null
        var clickPriceView: TextView? = null
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        totalSum = findViewById(R.id.totalSum)
        clickPriceView = findViewById(R.id.clickPrice)

        currentScore =  MainActivity.save?.getInt("currentScore", 0)!!
        clickPrice =  MainActivity.save?.getInt("clickPrice", 1)!!
        costImprove =  MainActivity.save?.getInt("cost_improve", 10)!!

        totalSum!!.text = "cookies\n $currentScore"
        clickPriceView!!.text = "cookies per click\n $clickPrice"
    }


    override fun onClick(p0: View?) {
        if(p0?.id == R.id.imgCookie){
            currentScore += clickPrice
            totalSum!!.text = "cookies\n $currentScore"
        }
        else if(p0?.id == R.id.shop){
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
            totalSum!!.text = "cookies\n $currentScore"
            clickPriceView!!.text = "cookies per click\n $clickPrice"
        }
        else if(p0?.id == R.id.game_exit){
            finish()
        }
    }

    private fun saveData(){
        val editor = MainActivity.save?.edit()
        editor?.putInt("currentScore", currentScore)
        editor?.putInt("clickPrice", clickPrice)
        editor?.putInt("cost_improve", costImprove)
        editor?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }
}