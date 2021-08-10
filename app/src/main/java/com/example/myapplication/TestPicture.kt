package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_test_code.*
import kotlinx.android.synthetic.main.activity_test_picture.*

class TestPicture : AppCompatActivity() {
    val tag = "tag"
    var db = FirebaseFirestore.getInstance()
    var dbCard = db.collection("card").document("card")
    val dbGetDataAll = db.collection("Room").document("Data")
    val dbSetSDataAll = db.collection("Room").document("Data")
    var card = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_picture)
        var   recyclerView:RecyclerView? = null
        //var recyclerView: RecyclerView? = null
        toggleBunton()

    }
    //TODO toggleBunton
    fun toggleBunton() {
        btnGetData_TestPicture.setOnClickListener {
            dbGetDataAll.addSnapshotListener { docs, error ->
                if (docs!!.exists()) { 
                    dbGetDataAll.delete()
                } else {

                }
            }
        }                                                                              
        btnBack_TestPicture.setOnClickListener {
            val intent = Intent(this,TestCode::class.java)
            startActivity(intent)
        }
    }

    //TODO Logd
    fun logd(msg: String) {
        Log.d(tag, msg)
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
