package com.example.myclicker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Shop : AppCompatActivity(), View.OnClickListener {
    var shop_total_sum: TextView? = null
    var buy: Button? = null
    var shop_click_price: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        shop_total_sum = findViewById(R.id.shop_total_sum)
        shop_click_price = findViewById(R.id.shop_click_price)
        buy = findViewById(R.id.buy)
        shop_total_sum!!.text = "cookies\n ${Game.currentScore}"
        shop_click_price!!.text = "cookies per click\n ${Game.clickPrice}"
        buy!!.text = "improve for ${Game.costImprove} cookie"
    }

    override fun onClick(p0: View?) {
        if(p0?.id == R.id.buy){
            if(Game.currentScore >= Game.costImprove) {
                Game.currentScore -= Game.costImprove
                Game.costImprove += Game.step
                Game.clickPrice++
                Game.totalSum!!.text = "cookies\n ${Game.currentScore}"
                shop_total_sum!!.text = "cookies\n ${Game.currentScore}"
                shop_click_price!!.text = "cookies per click\n ${Game.clickPrice}"
                Game.clickPriceView!!.text = "cookies per click\n ${Game.clickPrice}"
                buy!!.text = "improve for ${Game.costImprove} cookie"
            }
        }
        else if(p0?.id == R.id.shop_exit){
            finish()
        }
    }
}
