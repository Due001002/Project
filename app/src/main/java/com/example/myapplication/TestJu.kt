package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_test_ju.*
import kotlinx.android.synthetic.main.activity_test_ju.btnDelete
import kotlin.random.*

class TestJu : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    val TAG = "tag"
    var dbReadDataUser = db.collection("Room").document("Data").collection("User")
    val dbSetState = db.collection("Room").document("Data")
    val dbGetState = db.collection("Room").document("Data")
    var size = 0
    var state = 0
    var statusHost = ""
    var statusUser1 = ""
    var statusUser2 = ""
    var hostName = ""
    var user1Name = ""
    var user2Name = ""
    var valueHost = 0
    var valueUser1 = 0
    var valueUser2 = 0
    var pointHost = 0
    var pointUser1 = 0
    var pointUser2 = 0
    var keepName = ""
    var mutableListName = mutableListOf<Any>()
    var arrayName2 = mutableListOf<Any>()
    var getArrayname = mutableListOf<Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ju)
//        delete()
//        N_X_C()
//        addTest()
    }

    //TODO ADD_TEST
    fun addTest() {

        dbReadDataUser.addSnapshotListener { docs, error ->
            if (docs!!.size() >= 3) {
                dbReadDataUser.get().addOnSuccessListener {
                    if (it.size() <= 3) {
                        var getId = ""
                        var getData = ""
                        if (arrayName2.size < 3) {
                            for (doc in it!!) {
                                getId = doc.id
                                arrayName2.add(getId)
                            }
                            getArrayname.addAll(arrayName2)
                            logdfix("aaa", "arrayName2 = ${arrayName2}")
                            logdfix("aaa", "getArrayName2 = ${getArrayname}")
                            for (i in 0..getArrayname.size - 1) {
                                if ((getArrayname.contains(keepName))) {
                                    getArrayname.remove(keepName)
                                } else if (user1Name == "") {
                                    user1Name = getArrayname.get(0).toString()
                                    getArrayname.removeAt(0)
                                } else if (user2Name == "") {
                                    user2Name = getArrayname.get(0).toString()
                                    getArrayname.removeAt(0)
                                }
                            }
                            getData()
                        }
                        logdfix("aaa", "keepName = $keepName")
                        logdfix("aaa", "User1 = $user1Name")
                        logdfix("aaa", "User2 = $user2Name")
                    } else {
                        toast("ERROR IF")
                    }
                }
            }
        }
    }

    //TODO GETDATA
    fun getData() {
        var getName = ""
        var getValue = 0
        var getPoint = 0
        var status = ""
        var text = ""
        var getId = ""

        dbReadDataUser.addSnapshotListener { docs, error ->
            if (docs!!.size() >= 3) {
                size = docs.size()
                for (doc in docs!!) {
                    getId = doc.id

                    // TODO KeepName
                    if (keepName == getId) {
                        getName = doc.get("name").toString()
                        getValue = doc.get("value").toString().toInt()
                        getPoint = doc.get("point").toString().toInt()
                        text = doc.get("text").toString()
                        status = doc.get("status").toString()
                        state = doc.get("state").toString().toInt()
                        hostName = keepName
                        valueHost = getValue
                        pointHost = getPoint
                        statusHost = status
                        textView1.setText("$keepName /Value: $valueHost /Point: $pointHost")
                        textView2.setText("")
                        // state = getState!!.get("state").toString().toInt()
                        textViewStageTestJu.setText("State: $state")
                        if (status == "host") {
                            nextState()
                            if (state == 1) {
                                randomValue()
                            } else if (state == 2) {
                                calculator()
                            } else if (state == 3) {
                                clearText()
                            }
                        }
                    }
                    //-----------------------------------------------------
                    // TODO User1
                    else if (user1Name == getId) {
                        getName = doc.get("name").toString()
                        getValue = doc.get("value").toString().toInt()
                        getPoint = doc.get("point").toString().toInt()
                        status = doc.get("status").toString()
                        state = doc.get("state").toString().toInt()
                        text = doc.get("text").toString()
                        valueUser1 = getValue
                        pointUser1 = getPoint
                        statusUser1 = status
                        textView3.setText("$user1Name /Value: $valueUser1 /Point: $pointUser1")
                        textView4.setText("$text")
                    }
                    //-----------------------------------------------------
                    // TODO User2
                    else if (user2Name == getId) {
                        getName = doc.get("name").toString()
                        getValue = doc.get("value").toString().toInt()
                        getPoint = doc.get("point").toString().toInt()
                        status = doc.get("status").toString()
                        state = doc.get("state").toString().toInt()
                        text = doc.get("text").toString()
                        valueUser2 = getValue
                        pointUser2 = getPoint
                        statusUser2 = status
                        textView5.setText("$user2Name /Value: $valueUser2 /Point: $pointUser2")
                        textView6.setText("$text")
                    }
                    //-----------------------------------------------------
                }
            }   //TODO IF Size >= 1 : END
            else {
                textView1.setText("")
                textView2.setText("")
                textView3.setText("")
                textView4.setText("")
                textView5.setText("")
                textView6.setText("")
            }
        }
    }

    // TODO NEXT_STATE
    fun nextState() {
        dbReadDataUser.document(hostName).addSnapshotListener { docs, error ->
            btnNextTestJu.setOnClickListener {
                var getState = 0
                getState = docs!!.get("state").toString().toInt()
                getState++
                dbReadDataUser.document(hostName).update("state", getState)
                dbReadDataUser.document(user1Name).update("state", getState)
                dbReadDataUser.document(user2Name).update("state", getState)
            }
        }
    }

    //TODO Calculator
    fun calculator() {
        if (statusHost == "host") {
//            btncal.setOnClickListener {    TODO START BTN_CAL
//            var hostWin = mapOf("point" to pointHost + 50, "text" to "WIN")
//            var hostLose = mapOf("point" to pointHost - 50, "text" to "LOSE")
//            var user1Win = mapOf("point" to pointUser1 + 50, "text" to "WIN")
//            var user1Lose = mapOf("point" to pointUser1 - 50, "text" to "LOSE")
//            var user2Win = mapOf("point" to pointUser2 + 50, "text" to "WIN")
//            var user2Lose = mapOf("point" to pointUser2 - 50, "text" to "LOSE")
//            var draw = mapOf("text" to "DRAW")

//            var hostWin = mapOf("point" to pointHost, "text" to "WIN")
//            var hostLose = mapOf("point" to pointHost, "text" to "LOSE")
//            var user1Win = mapOf("point" to pointUser1, "text" to "WIN")
//            var user1Lose = mapOf("point" to pointUser1, "text" to "LOSE")
//            var user2Win = mapOf("point" to pointUser2, "text" to "WIN")
//            var user2Lose = mapOf("point" to pointUser2, "text" to "LOSE")
            var getPointHost = 0
            var getPointUser1 = 0
            var getPointUser2 = 0
            var resultPointHost = 0
            var resultPointUser1 = 0
            var resultPointUser2 = 0
            var win_Lose_Draw_Host = ""
            var win_Lose_Draw_User1 = ""
            var win_Lose_Draw_User2 = ""

            dbReadDataUser.document(keepName).get().addOnSuccessListener {
                getPointHost = it.get("point").toString().toInt()
                logdfix("pointaaa", "getPointHost: $getPointHost")
                dbReadDataUser.document(user1Name).get().addOnSuccessListener {
                    getPointUser1 = it.get("point").toString().toInt()
                    logdfix("pointaaa", "getPointUser1: $getPointUser1")
                    dbReadDataUser.document(user2Name).get().addOnSuccessListener {
                        getPointUser2 = it.get("point").toString().toInt()
                        logdfix("pointaaa", "getPointUser2: $getPointUser2")
                        if (valueHost > valueUser1) {
                            resultPointHost = getPointHost + 50
                            win_Lose_Draw_Host = "WIN"
                            resultPointUser1 = getPointUser1 - 50
                            win_Lose_Draw_User1 = "LOSE"
                        } else if (valueHost == valueUser1) {
                            resultPointHost = getPointHost
                            resultPointUser1 = getPointUser1
                            win_Lose_Draw_Host = "DRAW"
                            win_Lose_Draw_User1 = "DRAW"
                        } else {
                            resultPointHost = getPointHost - 50
                            win_Lose_Draw_Host = "LOSE"
                            resultPointUser1 = getPointUser1 + 50
                            win_Lose_Draw_User1 = "WIN"
                        }
                        //-----------------------------------------------------
                        if (valueHost > valueUser2) {
                            resultPointHost = resultPointHost + 50
                            win_Lose_Draw_Host = "WIN"
                            resultPointUser2 = getPointUser2 - 50
                            win_Lose_Draw_User2 = "LOSE"

                        } else if (valueHost == valueUser2) {
                            resultPointHost = getPointHost
                            resultPointUser2 = getPointUser2
                            win_Lose_Draw_Host = "DRAW"
                            win_Lose_Draw_User2 = "DRAW"
                        } else {
                            resultPointHost = resultPointHost - 50
                            win_Lose_Draw_Host = "LOSE"
                            resultPointUser2 = getPointUser2 + 50
                            win_Lose_Draw_User2 = "win"
                        }

                        var hostCal =
                            mapOf("point" to resultPointHost, "text" to win_Lose_Draw_Host)
                        var user1Cal =
                            mapOf("point" to resultPointUser1, "text" to win_Lose_Draw_User1)
                        var user2Cal =
                            mapOf("point" to resultPointUser2, "text" to win_Lose_Draw_User2)

                        dbReadDataUser.document(keepName).update(hostCal)
                        dbReadDataUser.document(user1Name).update(user1Cal)
                        dbReadDataUser.document(user2Name).update(user2Cal)
                    }
                }
            }
//TODO END BTNCAL }
        }
//            if (statusHost == "host") {
//                if (valueHost > valueUser1) {
//                    dbCC.document(host).update(hostWin)
//                    dbCC.document(user1).update(user1Lose)
//                } else if (valueHost == valueUser1) {
//                    dbCC.document(host).update(draw)
//                    dbCC.document(user1).update(draw)
//                } else {
//                    dbCC.document(host).update(hostLose)
//                    dbCC.document(user1).update(user1Win)
//                }
//                //-----------------------------------------------------
//                if (valueHost > valueUser2) {
//                    dbCC.document(host).update(hostWin)
//                    dbCC.document(user2).update(user2Lose)
//                } else if (valueHost == valueUser2) {
//                    dbCC.document(host).update(draw)
//                    dbCC.document(user2).update(draw)
//                } else {
//                    dbCC.document(host).update(hostLose)
//                    dbCC.document(user2).update(user2Win)
//                }
//            }
    }

    //TODO RESET
    fun randomValue() {
        state = 0
        var randomHost = Random.nextInt(0, 10)
        var randomUser1 = Random.nextInt(0, 10)
        var randomuser2 = Random.nextInt(0, 10)
        dbReadDataUser.document(keepName).update("value", randomHost)
        dbReadDataUser.document(user1Name).update("value", randomUser1)
        dbReadDataUser.document(user2Name).update("value", randomuser2)

//        var getId = ""
//        dbCC.addSnapshotListener { docs, error ->
//            btnreset.setOnClickListener {
//            for (doc in docs!!) {
//                getId = doc.id
//                var random = Random.nextInt(0, 10)
//                dbCC.document(getId).update("value", random)
//            }
//            }
//        }
    }

    //TODO CLEAR_TEXT
    fun clearText() {
//          dbCC.addSnapshotListener { docs, error ->
        dbReadDataUser.document(keepName).update("text", "")
        dbReadDataUser.document(user1Name).update("text", "")
        dbReadDataUser.document(user2Name).update("text", "")
        dbReadDataUser.document("C").update("state", "0")
//        }
    }

    //TODO N_X
    fun N_X_C() {
        btnN.setOnClickListener {
            var random = Random.nextInt(0, 10)
            var city = hashMapOf<String, Any>(
                "name" to "N",
                "point" to "150",
                "value" to 0,
                "status" to "host",
                "text" to "",
                "state" to 0
            )
            keepName = city.get("name").toString()
            dbReadDataUser.document("N").set(city)
            if (edtnametestju.text.toString().isEmpty()) {
                edtnametestju.setText("N")
            }
        }
        // -----------------------------------------------------
        btnX.setOnClickListener {
            var random = Random.nextInt(10)
            var city = hashMapOf<String, Any>(
                "name" to "X",
                "point" to "150",
                "value" to 0,
                "status" to "user",
                "text" to "",
                "state" to 0
            )
            keepName = city.get("name").toString()
            dbReadDataUser.document("X").set(city)
            if (edtnametestju.text.toString().isEmpty()) {
                edtnametestju.setText("X")
            }
        }
        //-----------------------------------------------------
        btnC.setOnClickListener {

            var random = Random.nextInt(0, 10)
            var city2 = hashMapOf<String, Any>(
                "name" to "C",
                "point" to "150",
                "value" to 0,
                "status" to "user",
                "text" to "",
                "state" to 0
            )
            keepName = city2.get("name").toString()
            dbReadDataUser.document("C").set(city2)

        }
    }

    //TODO Delete
    fun delete() {
        var random = Random.nextInt(1, 10)
        //-----------------------------------------------------
        dbReadDataUser.addSnapshotListener { docs, error ->
//            var getID = ""
//            var getData = ""
//            logdfix("aaa", "********************")
//            for (doc in docs!!) {
//                getData = doc.data.toString()
//                getID = doc.id
//                logdfix("aaa", "ID > $getID / DATA > $getData")
//            }
//            logdfix("aaa", "SIZE > ${docs.size()}")
//            logdfix("aaa", "********************")
            btnDelete.setOnClickListener {
//                dbCC.document(keepName).delete()
//                dbCC.document(user1).delete()
//                dbCC.document(user2).delete()
                arrayName2.removeAll(arrayName2)
                getArrayname.removeAll(getArrayname)
                logdfix("aaa", "arrayName2.Delete: $arrayName2")
                logdfix("aaa", "getArrayName.Delete: $getArrayname")
                user1Name = ""
                user2Name = ""
                var getID = ""
                var getData = ""
                for (doc in docs!!) {
                    getID = doc.id
                    dbReadDataUser.document(getID).delete()
                }
                dbSetState.delete()
            }
        }
    }

    // TODO getDataOnce
    private fun getDataOnce() {
        dbReadDataUser.document("C").get().addOnSuccessListener {
            if (it != null) {
                logdfix("one", "DATA READ ONE : ${it.data.toString()}")
                logdfix("one", "TEXT READ ONE : ${it.get("text")}")
            }
        }
    }

    //TODO TEST เอาไว้เทส
    fun testfix() {

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

