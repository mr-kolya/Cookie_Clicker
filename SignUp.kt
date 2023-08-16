package com.example.myclicker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity(), View.OnClickListener {
    var userLogin: EditText? = null
    var userPassword: EditText? = null
    var creat: Button? = null


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        userLogin = findViewById(R.id.sign_up_user_login)
        userPassword = findViewById(R.id.sign_up_user_password)
        creat = findViewById(R.id.create)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.create) {
            val login = userLogin?.text.toString().trim()
            val pass = userPassword?.text.toString().trim()
            if(login == "" || pass == ""){
                Toast.makeText( this, "Not all fields are filled in", Toast.LENGTH_LONG).show()
            }
            else{
                val user = User(login, pass)

                val db = DbHelper(this, null)
                db.addUser(user)

                Toast.makeText( this, "User $login added", Toast.LENGTH_LONG).show()
                userLogin?.text?.clear()
                userPassword?.text?.clear()
            }
        } else if (p0?.id == R.id.sign_up_exit) {
            finish()
        }
    }
}