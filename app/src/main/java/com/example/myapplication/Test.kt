package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class Test : AppCompatActivity() {
    val TAG = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

    }



    //TODO Logd
    fun logd(msg: String) {
        Log.d(TAG, msg)
    }

    //TODO Logd_Fix
    fun logdfix(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    //TODO Toast
    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}