package com.example.myclicker

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity(), View.OnClickListener {
    var userLogin: EditText? = null
    var userPassword: EditText? = null
    var enter: Button? = null


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userLogin = findViewById(R.id.login_user_login)
        userPassword = findViewById(R.id.login_user_password)
        enter = findViewById(R.id.enter)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.enter) {
            val login = userLogin?.text.toString().trim()
            val pass = userPassword?.text.toString().trim()
            if(login == "" || pass == ""){
                Toast.makeText( this, "Not all fields are filled in", Toast.LENGTH_LONG).show()
            }
            else {
                val db = DbHelper(this, null)

                val isAuth = db.getUser(login, pass)

                if (isAuth) {
                    MainActivity.save = getSharedPreferences("User $login password $pass", Context.MODE_PRIVATE)
                    MainActivity.login = true
                    Toast.makeText(this, "User $login is authorized", Toast.LENGTH_LONG).show()
                    userLogin?.text?.clear()
                    userPassword?.text?.clear()
                }
                else{
                    Toast.makeText(this, "User $login is not authorized", Toast.LENGTH_LONG).show()
                }
            }
        } else if (p0?.id == R.id.login_exit) {
            finish()
        }
    }
}