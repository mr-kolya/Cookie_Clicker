package com.example.myclicker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app", factory, 2) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, pass TEXT)"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(p0)
    }

    fun addUser(user: User){
        val values = ContentValues()
        values.put("login", user.login)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    fun getUser(login:String, pass:String): Boolean{
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)
        return result.moveToFirst()
    }
}