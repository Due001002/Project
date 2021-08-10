package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var db_testCode_creat = db.collection("testcode")
    var db_testCode_snapshot = db.collection("testcode").document("ty").collection("user")
    var namecheck = ""
    val TAG = "tag"

    //TODO ---------------------
    var user1 = ""
    var user2 = ""
    var user3 = ""
    var valueuser1 = 0
    var valueuser2 = 0
    var valueuser3 = 0
    var pointuser1 = 0
    var pointuser2 = 0
    var pointuser3 = 0
    var docValueBeforeInt = 0
    var textuser1 = ""
    var numtexttoast = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUser()
        Nubia_Xaio()
        getdata()
        calculator()
    }//TODO On_Create_END


// TODO --- FUNCTION_OPEN -----

    //TODO Get_Data
    fun getdata() {
        db_testCode_snapshot.addSnapshotListener { docs, err ->
            for (doc in docs!!) {
                var namedata = doc.get("name").toString()
                if (docs?.size()!! >= 3) {
                    if (doc.exists() && docs.size() >= 3) {
                        if (namecheck == namedata) {
                            txtNameLayout1.setText(namecheck)
                            user1 = txtNameLayout1.text.toString()
                            db_testCode_snapshot.whereEqualTo("name", "$user1").addSnapshotListener { value, er ->
                                for (result in value!!) {
                                    valueuser1 = result.get("value").toString().toInt()
                                    pointuser1 = result.get("point").toString().toInt()
                                    textuser1 = result.get("text").toString()
                                    txtNameLayout1.setText("$user1 / $valueuser1")
                                    txtPointLayout1.setText("Point:$pointuser1")
                                }

                                db_testCode_snapshot.whereEqualTo("text", textuser1).addSnapshotListener { docs, error ->
                                    for (doc in docs!!.documentChanges) {
                                        when (doc.type) {
                                            DocumentChange.Type.MODIFIED -> toast(doc.document.data.toString())
                                        }
                                    }
                                }
                                /* if (namecheck == doc.get("name").toString()) {
                                     when (textuser1) {
                                         "WIN" -> toast("WIN")
                                         "LOSE" -> toast("LOSE")
                                     }
                                 }*/
                            }
                        } else if (txtNameLayout2.text.toString().isEmpty()) {
                            txtNameLayout2.setText(namedata)
                            user2 = txtNameLayout2.text.toString()
                            db_testCode_snapshot.whereEqualTo("name", "$user2").addSnapshotListener { value, er ->
                                for (result in value!!) {
                                    valueuser2 = result.get("value").toString().toInt()
                                    pointuser2 = result.get("point").toString().toInt()
                                    txtNameLayout2.setText("$user2 / $valueuser2")
                                    txtPointLayout2.setText("Point:$pointuser2")
                                }
                            }
                        } else if (txtNameLayout3.text.toString().isEmpty()) {
                            txtNameLayout3.setText(namedata)
                            user3 = txtNameLayout3.text.toString()
                            db_testCode_snapshot.whereEqualTo("name", "$user3").addSnapshotListener { value, er ->
                                for (result in value!!) {
                                    valueuser3 = result.get("value").toString().toInt()
                                    pointuser3 = result.get("point").toString().toInt()
                                    txtNameLayout3.setText("$user3 / $valueuser3")
                                    txtPointLayout3.setText("Point:$pointuser3")
                                }
                            }
                        }
                    }

                    logdfix("user", "user1: $user1")
                    logdfix("user", "user2: $user2")
                    logdfix("user", "user3: $user3")

                }
            }

        }
//        val size = if (
//                docs?.documents?.size != null
//        ) {
//            docs?.documents?.size
//        } else {
//            0
//        }
//        if (size != 0) {
//            for (i in 0 until (docs?.documents?.size!!)) {
//                val doc = docs?.documents?.get(i)!!
//                var namedata = doc.get("name").toString()
//                //logdfix("data", "name: ${namedata}")
//                logdfix("data", "----------------------------")
//                //TODO =============================
//                if (i == 0 && namecheck == namedata) {
//                    user1 = doc.get("name").toString()
//                    valueuser1 = doc?.get("value").toString().toInt()
//                    pointuser1 = doc!!.get("point").toString().toInt()
//                    textuser1 = doc?.get("text").toString()
//                    txtNameLayout1.setText("$user1 / $valueuser1")
//                    txtPointLayout1.setText("Point: $pointuser1")
//                } else if (i == 1) {
//                    user2 = doc.get("name").toString()
//                    valueuser2 = doc?.get("value").toString().toInt()
//                    pointuser2 = doc?.get("point").toString().toInt()
//                    txtNameLayout2.setText("$user2 / $valueuser2")
//                    txtPointLayout2.setText("Point: $pointuser2")
//                } else if (i == 2) {
//                    user3 = doc.get("name").toString()
//                    valueuser3 = doc?.get("value").toString().toInt()
//                    pointuser3 = doc?.get("point").toString().toInt()
//                    txtNameLayout3.setText("$user3 / $valueuser3")
//                    txtPointLayout3.setText("Point: $pointuser3")
//                }
//            }
//        }
    }

    //TODO calculator
    fun calculator() {
        btnCalculator.setOnClickListener {//TODO BTN_Calculator
            var win = mapOf("text" to "WIN")
            var lose = mapOf("text" to "LOSE")
            var draw = mapOf("text" to "DRAW")
            db_testCode_snapshot.addSnapshotListener { docs, error ->
                for (doc in docs!!) {
                    docValueBeforeInt = doc.data.get("point").toString().toInt()
                }
                if (docValueBeforeInt != 0) {
                    if (namecheck[0].equals('n')) {
                        if (valueuser1 > valueuser2 || valueuser1 == valueuser2) {
                            var updateuser1 = hashMapOf("point" to pointuser1 + 50, "text" to "WIN")
                            var updateuser2 = hashMapOf("point" to pointuser2 - 50, "text" to "LOSE")
                            var updateuserDraw1 = hashMapOf("point" to pointuser1 + 0, "text" to "DRAW")
                            var updateuserDraw2 = hashMapOf("point" to pointuser2 + 0, "text" to "DRAW")
                           // var updateuserDraw3 = updateValue(pointuser2 + 0, "DRAW")
                        }
                    }
                }
            }
        }
    }

    // TODO ADD_USER
    fun addUser() {
        btnSendName.setOnClickListener {
            if (!edtNameSend.text.isNullOrEmpty()) {
                namecheck = edtNameSend.text.toString()
                var random = Random.nextInt(1, 10)
                var user = hashMapOf("name" to namecheck,
                        "point" to 500,
                        "value" to random,
                        "text" to "")
                db_testCode_snapshot.document(namecheck).set(user).addOnSuccessListener { toast("Add Success") }
                edtNameSend.setText("")
            } else toast("Specify name")

        }
    }

    //TODO Sendname_Nubia_Xaio
    fun Nubia_Xaio() {
        btnNubia.setOnClickListener {
            edtNameSend.setText("")
            edtNameSend.setText("nubia")
            if (edtNameSend.text.toString() == "nubia") {
                btnNubia.setOnClickListener {
                    var resulte = edtNameSend.text.toString()
                    edtNameSend.setText("$resulte" + "nubia")
                }
            }
        }
        btnXaio.setOnClickListener {
            edtNameSend.setText("")
            edtNameSend.setText("xaio")
            if (edtNameSend.text.toString() == "xaio") {
                btnXaio.setOnClickListener {
                    var resulte = edtNameSend.text.toString()
                    edtNameSend.setText("$resulte" + "xaio")
                }
            }
        }
    }

    //TODO Toast
    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    //TODO Logd
    fun logd(msg: String) {
        Log.d(TAG, msg)
    }

    //TODO Logd_Fix
    fun logdfix(tag: String, msg: String) {
        Log.d(tag, msg)
    }
//TODO --- FUNCTION_OFF ---
}
