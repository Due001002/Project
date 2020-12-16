package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var dbsnapdata = db.collection("room").document("room").collection("room")
    var dbdeletedata = db.collection("room").document("room").collection("room")
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
    var textuser1 = ""
    var numtexttoast = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUser()
        sendname()
        getdata()
        delete()
    }//TODO On_Create_END


// TODO --- FUNCTION_OPEN -----

    //TODO Get_Data
    fun getdata() {
        dbsnapdata.addSnapshotListener { docs, err ->
            for (doc in docs!!) {
                var namedata = doc.get("name").toString()
                    //logdfix("data", "name: ${namedata}")
                logdfix("data", "----------------------------")
                //TODO =============================
                if (docs.size() >= 3) {
                    if (doc.exists() && docs.size() >= 3) {
                        if (namecheck == namedata) {
                            txtNameLayout1.setText(namecheck)
                            user1 = txtNameLayout1.text.toString()
                            dbsnapdata.whereEqualTo("name","$user1").addSnapshotListener { value, er ->
                                for (result in value!!) {
                                    valueuser1 = result.get("value").toString().toInt()
                                    pointuser1 = result.get("point").toString().toInt()
                                    textuser1 = result.get("text").toString()
                                    txtNameLayout1.setText("$user1 / $valueuser1")
                                    txtPointLayout1.setText("Point:$pointuser1")
                                }

                                if (namecheck == doc.get("name").toString()) {
                                    when (textuser1) {
                                        "WIN" -> toast("WIN")
                                        "LOSE" -> toast("LOSE")
                                    }
                                }
                            }
                        } else if (txtNameLayout2.text.toString().isEmpty()) {
                            txtNameLayout2.setText(namedata)
                            user2 = txtNameLayout2.text.toString()
                            dbsnapdata.whereEqualTo("name","$user2").addSnapshotListener { value, er ->
                                for (result in value!!) {
                                    valueuser2 = result.get("value").toString().toInt()
                                    pointuser2  = result.get("point").toString().toInt()
                                    txtNameLayout2.setText("$user2 / $valueuser2")
                                    txtPointLayout2.setText("Point:$pointuser2")
                                }
                            }
                        } else if (txtNameLayout3.text.toString().isEmpty()) {
                            txtNameLayout3.setText(namedata)
                            user3 = txtNameLayout3.text.toString()
                            dbsnapdata.whereEqualTo("name","$user3").addSnapshotListener { value, er ->
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

                    btnCalculator.setOnClickListener {  //TODO BTN_Calculator
                        if (namecheck[0].equals('n')) {
                            if (valueuser1 > valueuser2) {
                                var updateuser1 = hashMapOf("point" to pointuser1 + 50, "text" to "WIN")
                                var updateuser2 = hashMapOf("point" to pointuser2 - 50, "text" to "LOSE")
                                dbsnapdata.document("$user1").update(updateuser1)
                                dbsnapdata.document("$user2").update(updateuser2)
                            } else {
                                var updateuser1 = hashMapOf("point" to pointuser1 - 50, "text" to "LOSE")
                                var updateuser2 = hashMapOf("point" to pointuser2 + 50, "text" to "WIN")
                                dbsnapdata.document("$user1").update(updateuser1)
                                dbsnapdata.document("$user2").update(updateuser2)
                            }
                            if (valueuser1 > valueuser3) {
                                var updateuser1 = hashMapOf("point" to pointuser1 + 50, "text" to "WIN")
                                var updateuser3 = hashMapOf("point" to pointuser3 - 50, "text" to "LOSE")
                                dbsnapdata.document("$user1").update(updateuser1)
                                dbsnapdata.document("$user3").update(updateuser3)
                            } else {
                                var updateuser1 = hashMapOf("point" to pointuser1 - 50, "text" to "LOSE")
                                var updateuser3 = hashMapOf("point" to pointuser3 + 50, "text" to "WIN")
                                dbsnapdata.document("$user1").update(updateuser1)
                                dbsnapdata.document("$user3").update(updateuser3)
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
                namecheck = edtNameSend.text.toString()
                var random = Random.nextInt(1, 10)
                var user = hashMapOf("name" to namecheck,
                        "point" to 200,
                        "value" to random,
                        "text" to "")

                dbsnapdata.document(namecheck).set(user).addOnSuccessListener { toast("Add Success") }
                edtNameSend.setText("")
            } else toast("Specify name")

        }
    }

    //TODO Delete
    fun delete() {
            dbsnapdata.addSnapshotListener { docs, er ->
                btnDelete.setOnClickListener {
                    if (docs != null) {
                        for (doc in docs) {
                            var data = doc.get("name").toString()
                            dbdeletedata.document(data).delete()
                        }
                        toast("Delete")
                        txtNameLayout1.setText("")
                        txtNameLayout2.setText("")
                        txtNameLayout3.setText("")
                        txtPointLayout1.setText("")
                        txtPointLayout2.setText("")
                        txtPointLayout3.setText("")
                    }
                }
            }
    }

//TODO Sendname_Nubia_Xaio
fun sendname() {
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
//TODO --- FUNCTION_OFF ---