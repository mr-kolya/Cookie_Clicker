package com.example.myclicker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        var save: SharedPreferences? = null
        var login: Boolean = false
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun ClearAll(){
        val editor = save?.edit()
        editor?.clear()
        editor?.apply()
    }

    override fun onClick(p0: View?) {
        if(!login && (p0?.id == R.id.new_game || p0?.id == R.id.main_continue)){
            Toast.makeText(this, "You are not registered", Toast.LENGTH_LONG).show()
        }
        if(p0?.id == R.id.sign_up){
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        if(p0?.id == R.id.login){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        if(login && p0?.id == R.id.new_game){
            ClearAll()
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        }
        if(login && p0?.id == R.id.main_continue){
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        }
        if(p0?.id == R.id.about){
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
        if(p0?.id == R.id.main_exit){
            finish()
        }
    }
}
