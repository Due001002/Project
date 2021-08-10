package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_test_code.*
import kotlin.random.Random

class TestCode : AppCompatActivity() {
    val TAG = "tag"
    var db = FirebaseFirestore.getInstance()
    var dbReadDataUser = db.collection("Room").document("Data").collection("User")
    val dbSetSDataAll = db.collection("Room").document("Data")
    val dbGetDataAll = db.collection("Room").document("Data")
    val dbCollectInformation = db.collection("Room").document("CollectInformation")
    val getNameField = "name"
    val getValueField = "value"
    val getPointField = "point"
    val getStatusField = "status"
    val getTextField = "text"
    val getCardField = "card"
    val getRoundField = "round"
    var statusHost = ""
    var statusUser1 = ""
    var statusUser2 = ""
    var keepName = ""
    var jao = "เจ้ามือ"
    var state = 0
    var Host = hashMapOf<String, Any>()
    var valueHost = 0
    var valueUser1 = 0
    var valueUser2 = 0
    var User1 = hashMapOf<String, Any>()
    var User2 = hashMapOf<String, Any>()
    var pointHost = 0
    var pointUser1 = 0
    var pointUser2 = 0
    val getStateField = "state"
    val getUsersString = "users"
    var getCardDB = mutableListOf<String>()
    var test = mutableListOf<Int>(1, 2, 5, 4, 6)
    var backCard = mutableListOf<String>("n", "n")
    var cardHost = mutableListOf<String>()
    var cardUser1 = mutableListOf<String>()
    var cardUser2 = mutableListOf<String>()
    var card = mutableListOf<String>(
        "1fj", "2fj", "3fj", "4fj", "5fj", "6fj", "7fj", "8fj", "9fj", "10fj",
        "1kl", "2kl", "3kl", "4kl", "5kl", "6kl", "7kl", "8kl", "9kl", "10kl",
        "1pb", "2pb", "3pb", "4pb", "5pb", "6pb", "7pb", "8pb", "9pb", "10pb",
        "1pr", "2pr", "3pr", "4pr", "5pr", "6pr", "7pr", "8pr", "9pr", "10pr",
        "jfj", "jkl", "jpb", "jpr",
        "kfj", "kkl", "kpb", "kpr",
        "qfj", "qkl", "qpb", "qpr"
    )
    //var userListForset = listOf<Map<String, Any>>()
    var checkRoundForLoop = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_code)
        btnStartTestju_testCode.isVisible = false
        N_C_X()
        Delete()
        logdfix("dsawesdfg4er", "test.sortDescending() : ${test.sortDescending()}")
        logdfix("dsawesdfg4er", "test.sortedDescending() : ${test.sortedDescending()}}")
    }

    //TODO N_C_X
    fun N_C_X() {
        //TODO --------------------------------------
        btnN_testCode.setOnClickListener {
            dbGetDataAll.get().addOnSuccessListener {
                if (it != null && it.exists()) {
                    var getData = it!!.get(getUsersString) as MutableList<Map<String, Any>>
                    keepName = "nubia"
                    var userListForset = listOf<Map<String, Any>>(
                        mapOf(
                            getNameField to keepName,
                            getValueField to 0,
                            getPointField to 150,
                            getStatusField to "user",
                            getCardField to backCard,
                            getTextField to ""
                        ),
                        mapOf(
                            getNameField to "BOT",
                            getValueField to 0,
                            getPointField to 150,
                            getStatusField to "user",
                            getCardField to backCard,
                            getTextField to ""
                        )
                    )
                    for (i in 0..userListForset.size - 1) {
                        getData.add(userListForset.get(i))
                    }
                    var userMap = mapOf(
                        getStateField to 0,
                        getUsersString to getData
                    )
                    dbGetDataAll.set(userMap)
                } else {
                    keepName = "nubia"
                    var userListForset = listOf<Map<String, Any>>(
                        mapOf(
                            getNameField to keepName,
                            getValueField to 0,
                            getPointField to 150,
                            getStatusField to "user",
                            getCardField to backCard,
                            getTextField to ""
                        ),
                        mapOf(
                            getNameField to "BOT",
                            getValueField to 0,
                            getPointField to 150,
                            getStatusField to "user",
                            getCardField to backCard,
                            getTextField to ""
                        )
                    )
                    //getData.add(userListForset)
                    var userMap = mapOf(
                        getStateField to 0,
                        getUsersString to userListForset
                    )
                    dbGetDataAll.set(userMap)
                }
            }
        }

        //TODO --------------------------------------
        btnX_testCode.setOnClickListener {
            // DO Something
        }

        //TODO --------------------------------------
        btnC_testCode.setOnClickListener {
            dbGetDataAll.get().addOnSuccessListener {
                if (it!!.exists()) {
                    var getData = it!!.get(getUsersString) as MutableList<Map<String, Any>>
                    keepName = "Xaio"
                    var userListForset = listOf<Map<String, Any>>(

                        mapOf(
                            getNameField to keepName,
                            getValueField to 0,
                            getPointField to 150,
                            getStatusField to "host",
                            getCardField to backCard,
                            getTextField to jao
                        )
                    )
                    getData.add(userListForset.get(0))
                    var setupData = mapOf(
                        getRoundField to 1,
                        getStateField to 0,
                        getUsersString to getData,
                        getCardField to card
                    )
                    dbGetDataAll.set(setupData)
                } else {
                    keepName = "Xaio"
                    var userListForset = listOf<Map<String, Any>>(
                        mapOf(
                            getNameField to keepName,
                            getValueField to 0,
                            getPointField to 150,
                            getStatusField to "host",
                            getCardField to backCard,
                            getTextField to jao
                        )
                    )
                    var userMap = mapOf("state" to 0, getUsersString to userListForset)
                    dbGetDataAll.set(userMap)
                }
            }
        }

        //TODO check_user == 3
        dbGetDataAll.addSnapshotListener { docs, error ->
            if (docs != null && docs.exists()) {
                var getData = docs.get(getUsersString) as MutableList<Map<String, Any>>
                if (getData.size == 3) {
                    addUser()
                } else {

                }
            } else {
                logdfix("aaa", "Open Else")
            }
        }
    }

    //TODO Add_User
    fun addUser() {
        dbGetDataAll.get().addOnSuccessListener {
            var getDataSize = it!!.get(getUsersString) as List<Map<String, Any>>
            if (getDataSize.size == 3) {
                for (index in 0..getDataSize.size - 1) {
                    var checkKeyname = getDataSize.get(index)
                    logdfix("aaa", "[$index]: ${getDataSize}")
                    if (checkKeyname.get("name") == keepName) {
                        Host = getDataSize[index] as HashMap<String, Any>

                    } else if (User1.isEmpty()) {
                        User1 = getDataSize[index] as HashMap<String, Any>
                    } else if (User2.isEmpty()) {
                        User2 = getDataSize[index] as HashMap<String, Any>

                    }
                }
                getData()
                logdfix("aaa", "HOST: $Host")
                logdfix("aaa", "User1: $User1")
                logdfix("aaa", "User2: $User2")
                logdfix("aaa", "----------END-------------")
            }
        }
//        dbGetDataAll.get().addOnSuccessListener { docs ->
//            var getData = docs!!.get(getUsersString) as List<Map<String, Any>>
//            var getDataState = docs!!.get("state").toString().toInt()
//            var setData = mapOf(getStateString to getDataState, getUsersString to getData)
//            dbSetSDataAll.set(setData).addOnSuccessListener {
//            }
//            logdfix("aaa", "----------Begin-------------")
//        }
    }

    //TODO getData
    fun getData() {
        dbGetDataAll.addSnapshotListener { docs, error ->
            //TODO Show_UI
            if (docs!!.exists()) {
                if (Host.get("status").toString() == "host") {
                    var getState = docs!!.get(getStateField).toString().toInt()
                    nextState()
                    btnStartTestju_testCode.isVisible = true
                }

                //TODO READ_DATA
                var getDataUsersSize = docs!!.get(getUsersString) as ArrayList<Map<String, Any>>
                if (getDataUsersSize.size == 3) {
                    var state = docs!!.get("state").toString().toInt()
                    var round = docs!!.get("round").toString().toInt()
                    var getDataUsers = docs!!.get(getUsersString) as ArrayList<Map<String, Any>>
                    var getKeyName = getDataUsers
                    var host = Host.get("name").toString()
                    var user1 = User1.get("name").toString()
                    var user2 = User2.get("name").toString()
                    logdfix("doocard", "Host: ${Host}")
                    logdfix("doocard", "Host: ${User1}")
                    logdfix("doocard", "Host: ${User2}")

                    for (doc in getKeyName) {
                        var card = doc!!.get("card") as MutableList<String>
                        if (doc.containsValue(host)) {
                            valueHost = doc.get("value").toString().toInt()
                            pointHost = doc.get("point").toString().toInt()
                            statusHost = doc.get("status").toString()
                            var text = doc.get("text").toString()
                            if (statusHost == "host") {
                                textView1_testCode.setText("$host/แต้ม:?/คะแนน:$pointHost")
                                textView2_testCode.setText(text)
                                if (state >= 1) {
                                    textView1_testCode.setText("$host/แต้ม:$valueHost/คะแนน:$pointHost")
                                    textView2_testCode.setText(text)
                                }
                            } else {
                                textView1_testCode.setText("$host/แต้ม:?/คะแนน:$pointHost")
                                textView2_testCode.setText(text)
                                if (state >= 1) {
                                    textView1_testCode.setText("$host/แต้ม:$valueHost/คะแนน:$pointHost")
                                    textView2_testCode.setText(text)
                                }
                            }
                            imageView1_TestCode.setImageResource(getCardShow(card[0]))
                            imageView2_TestCode.setImageResource(getCardShow(card[1]))
                            textViewStageTestJu_testCode.setText("Round:$round/state:$state")
                            if (round == 2) {
                                val intent = Intent(this, TestPicture::class.java)
                                startActivity(intent)
                            }
                        } else if (doc.containsValue(user1)) {
                            valueUser1 = doc.get("value").toString().toInt()
                            pointUser1 = doc.get("point").toString().toInt()
                            statusUser1 = doc.get("status").toString()

                            var text = doc.get("text").toString()
                            textView3_testCode.setText("$user1/แต้ม:?/คะแนน:$pointUser1")
                            textView4_testCode.setText(text)
                            if (valueUser1 == 8 || valueUser1 == 9) {
                                imageView4_TestCode.setImageResource(getCardShow(card[0]))
                                imageView5_TestCode.setImageResource(getCardShow(card[1]))
                            }
                            if (state > 1) {
                                textView3_testCode.setText("$user1/แต้ม:$valueUser1/คะแนน:$pointUser1")
                                imageView4_TestCode.setImageResource(getCardShow(card[0]))
                                imageView5_TestCode.setImageResource(getCardShow(card[1]))
                            }
                        } else if (doc.containsValue(user2)) {
                            var text = doc.get("text").toString()
                            valueUser2 = doc.get("value").toString().toInt()
                            pointUser2 = doc.get("point").toString().toInt()
                            statusUser2 = doc.get("status").toString()
                            textView5_testCode.setText("$user2/แต้ม:?/คะแนน:$pointUser2")
                            textView6_testCode.setText(text)
                            if (valueUser2 == 8 || valueUser2 == 9) {
                                imageView7_TestCode.setImageResource(getCardShow(card[0]))
                                imageView8_TestCode.setImageResource(getCardShow(card[1]))
                            }
                            if (state > 1) {
                                textView5_testCode.setText("$user2/แต้ม:$valueUser2/คะแนน:$pointUser2")
                                imageView7_TestCode.setImageResource(getCardShow(card[0]))
                                imageView8_TestCode.setImageResource(getCardShow(card[1]))
                            }
                        }
                        checkRoundForLoop++
                    }
                }
            } else { //TODO getDataUsersSize.size == 3
                btnStartTestju_testCode.isVisible = false
                textView1_testCode.setText("")
                textView2_testCode.setText("")
                textView3_testCode.setText("")
                textView4_testCode.setText("")
                textView5_testCode.setText("")
                textView6_testCode.setText("")
                textViewStageTestJu_testCode.setText("")
                imageView1_TestCode.setImageResource(R.drawable.backphai)
                imageView2_TestCode.setImageResource(R.drawable.backphai)
                imageView3_TestCode.setImageResource(R.drawable.backphai)
                imageView4_TestCode.setImageResource(R.drawable.backphai)
                imageView5_TestCode.setImageResource(R.drawable.backphai)
                imageView6_TestCode.setImageResource(R.drawable.backphai)
                imageView7_TestCode.setImageResource(R.drawable.backphai)
                imageView8_TestCode.setImageResource(R.drawable.backphai)
                imageView9_TestCode.setImageResource(R.drawable.backphai)
            }
        }
    }

    //TODO NexState
    fun nextState() {
        btnStartTestju_testCode.setOnClickListener {
            dbGetDataAll.get().addOnSuccessListener {
                var getRound = it!!.get("round").toString().toInt()
                var getState = it!!.get("state").toString().toInt()
                getCardDB = it!!.get("card") as MutableList<String>
                var sumState = getState + 1
                state = sumState
                var setData = mapOf<String, Any>("state" to sumState)

                if (state <= 3) {
                    when (state) {
                        1 -> {
                            stage1_GiveCard();dbSetSDataAll.update(setData)
                        }
                        2 -> {
                            state2_BattleCard();dbSetSDataAll.update(setData)
                        }
                        3 -> {
                            state3_ReStart();dbSetSDataAll.update(setData)
                        }
                    }
                } else {
                    getRound += 1
                    var setupData = mapOf(
                        getRoundField to getRound,
                        getStateField to 0
                    )
                    dbSetSDataAll.update(setupData)
                }
            }
            checkRoundForLoop = 0
        }
    }

    //TODO Give_GetCard Stage:1
    fun stage1_GiveCard() {
        var totalHost = 0
        var totalUser1 = 0
        var totalUser2 = 0
        cardHost.clear()
        cardUser1.clear()
        cardUser2.clear()
        getCardDB.shuffle()
        cardHost.add("8pb")
        cardHost.add("1pb")
//        cardHost.add(getCardDB[0])
//        cardHost.add(getCardDB[1])
        cardUser1.add(getCardDB[2])
        cardUser1.add(getCardDB[3])
        cardUser2.add(getCardDB[4])
        cardUser2.add(getCardDB[5])
        var rsIdCardHost_1 = getCardString(cardHost[0])
        var rsIdCardHost_2 = getCardString(cardHost[1])
        var rsIdCardHost_3 = 0
        var rsIdCardUser1_1 = getCardString(cardUser1[0])
        var rsIdCardUser1_2 = getCardString(cardUser1[1])
        var rsIdCardUser1_3 = 0
        var rsIdCard2_1 = getCardString(cardUser2[0])
        var rsIdCard2_2 = getCardString(cardUser2[1])
        var rsIdCard2_3 = 0
        getCardDB.removeAt(0)
        getCardDB.removeAt(0)
        getCardDB.removeAt(0)
        getCardDB.removeAt(0)
        getCardDB.removeAt(0)
        getCardDB.removeAt(0)
        totalHost = calculatorValue(rsIdCardHost_1.value, rsIdCardHost_2.value)
        totalUser1 = calculatorValue(rsIdCardUser1_1.value, rsIdCardUser1_2.value)
        totalUser2 = calculatorValue(rsIdCard2_1.value, rsIdCard2_2.value)

        var updateUser1 = mutableListOf<Map<Any, Any>>(
            mapOf(
                getNameField to Host.get("name").toString(),
                getValueField to totalHost,
                getPointField to Host.get("point").toString(),
                getStatusField to Host.get("status").toString(),
                getTextField to jao,
                getCardField to cardHost
            ),
            mapOf(
                getNameField to User1.get("name").toString(),
                getValueField to totalUser1,
                getPointField to User1.get("point").toString(),
                getStatusField to User1.get("status").toString(),
                getTextField to User1.get("text").toString(),
                getCardField to cardUser1
            ),
            mapOf(
                getNameField to User2.get("name").toString(),
                getValueField to totalUser2,
                getPointField to User2.get("point").toString(),
                getStatusField to User2.get("status").toString(),
                getTextField to User2.get("text").toString(),
                getCardField to cardUser2
            )
        )

        var update = mapOf(
            getCardField to getCardDB,
            getUsersString to updateUser1
        )
        dbSetSDataAll.update(update)
        toast("Toggle")
    }

    //TODO battleCard
    fun state2_BattleCard() {
        var totalPointHost = pointHost
        var totalPointUser1 = pointUser1
        var totalPointUser2 = pointUser2
        val win = "ชนะ"
        val lose = "แพ้"
        val draw = "เสมอ"
        var textUser1 = ""
        var textUser2 = ""
        val getStringCardHost1 = "${cardHost.get(0).get(1)}${cardHost.get(0).get(2)}"
        val getStringCardHost2 = "${cardHost.get(1).get(1)}${cardHost.get(1).get(2)}"
        val getStringCardUser1 = "${cardUser1.get(0).get(1)}${cardHost.get(0).get(2)}"
        val getStringCardUser1_2 = "${cardUser1.get(1).get(1)}${cardHost.get(1).get(2)}"
        val getStringCardUser2 = "${cardUser2.get(0).get(1)}${cardHost.get(0).get(2)}"
        val getStringCardUser2_2 = "${cardUser2.get(1).get(1)}${cardHost.get(1).get(2)}"

        //TODO Cal
        if (valueHost > valueUser1 || valueHost < valueUser1 || valueHost == valueUser1) {
            if (valueHost > valueUser1) {
                if (getStringCardHost1 == getStringCardHost2) {
                    totalPointHost += 100
                    totalPointUser1 -= 100
                    textUser1 = lose
                } else {
                    totalPointHost += 50
                    totalPointUser1 -= 50
                    textUser1 = lose
                }
            } else if (valueHost < valueUser1) {
                if (getStringCardUser1 == getStringCardUser1_2) {
                    totalPointHost -= 100
                    totalPointUser1 += 100
                    textUser1 = win
                } else {
                    totalPointHost -= 50
                    totalPointUser1 += 50
                    textUser1 = win
                }
            } else if (valueHost == valueUser1) {
                textUser1 = draw
            }
        }

        if (valueHost > valueUser2 || valueHost < valueUser2 || valueHost == valueUser2) {
            if (valueHost > valueUser2) {
                if (getStringCardHost1 == getStringCardHost2) {
                    totalPointHost += 100
                    totalPointUser2 -= 100
                    textUser2 = lose
                } else {
                    totalPointHost += 50
                    totalPointUser2 -= 50
                    textUser2 = lose
                }
            } else if (valueHost < valueUser2) {
                if (getStringCardHost2 == getStringCardUser2_2) {
                    totalPointHost -= 100
                    totalPointUser2 += 100
                    textUser2 = win
                } else {
                    totalPointHost -= 50
                    totalPointUser2 += 50
                    textUser2 = win
                }
            } else if (valueHost == valueUser2) {
                textUser2 = draw
            }
        }
        var updateDataUser = listOf<Map<String, Any>>(
            mapOf(
                getNameField to Host.get("name").toString(),
                getValueField to valueHost,
                getPointField to totalPointHost,
                getStatusField to Host.get("status").toString(),
                getTextField to jao,
                getCardField to cardHost
            ),
            mapOf(
                getNameField to User1.get("name").toString(),
                getValueField to valueUser1,
                getPointField to totalPointUser1,
                getStatusField to User1.get("status").toString(),
                getTextField to textUser1,
                getCardField to cardUser1
            ),
            mapOf(
                getNameField to User2.get("name").toString(),
                getValueField to valueUser2,
                getPointField to totalPointUser2,
                getStatusField to User2.get("status").toString(),
                getTextField to textUser2,
                getCardField to cardUser2
            )
        )
        var setDataCal = mapOf(
            getUsersString to updateDataUser
        )
        dbSetSDataAll.update(setDataCal)
    }

    //TODO State3_ReStart
    fun state3_ReStart() {
        var updateDataUser = listOf<Map<String, Any>>(
            mapOf(
                getNameField to Host.get("name").toString(),
                getValueField to 0,
                getPointField to pointHost,
                getStateField to statusHost,
                getTextField to jao,
                getCardField to backCard
            ),
            mapOf(
                getNameField to User1.get("name").toString(),
                getValueField to 0,
                getPointField to pointUser1,
                getStatusField to statusUser1,
                getTextField to "",
                getCardField to backCard
            ),
            mapOf(
                getNameField to User2.get("name").toString(),
                getValueField to 0,
                getPointField to pointUser2,
                getStatusField to statusUser2,
                getTextField to "",
                getCardField to backCard
            )
        )
        var setupDataUser = mapOf(
            getCardField to card,
            getUsersString to updateDataUser
        )
        dbSetSDataAll.update(setupDataUser)
    }

    // TODO randomValue
    fun randomValue() {
        var setData = mapOf<String, Any>()
        var randomhost = Random.nextInt(1, 10)
        var randomuser1 = Random.nextInt(1, 10)
        var randomuser2 = Random.nextInt(1, 10)
    }

    //TODO calculatorValue
    fun calculatorValue(a: Int, b: Int): Int {
        var total = a + b
        if (total > 9) {
            total = total - 10
        }
        return total
    }

    //TODO Delete
    fun Delete() {
        dbGetDataAll.addSnapshotListener { docs, error ->
            if (docs!!.exists()) {
                btnDelete_testCode.setOnClickListener {
                    dbGetDataAll.delete()
                    Host.clear()
                    User1.clear()
                    User2.clear()
                }

            } else {

            }
        }
    }

    //TODO getCard_String
    fun getCardShow(a: String): Int {
        var resource = 0
        when (a) {
            "1fj" -> resource = R.drawable.flowerjik1
            "2fj" -> resource = R.drawable.flowerjik2
            "3fj" -> resource = R.drawable.flowerjik3
            "4fj" -> resource = R.drawable.flowerjik4
            "5fj" -> resource = R.drawable.flowerjik5
            "6fj" -> resource = R.drawable.flowerjik6
            "7fj" -> resource = R.drawable.flowerjik7
            "8fj" -> resource = R.drawable.flowerjik8
            "9fj" -> resource = R.drawable.flowerjik9
            "10fj" -> resource = R.drawable.flowerjik10
            "1kl" -> resource = R.drawable.khaolam1
            "2kl" -> resource = R.drawable.khaolam2
            "3kl" -> resource = R.drawable.khaolam3
            "4kl" -> resource = R.drawable.khaolam4
            "5kl" -> resource = R.drawable.khaolam5
            "6kl" -> resource = R.drawable.khaolam6
            "7kl" -> resource = R.drawable.khaolam7
            "8kl" -> resource = R.drawable.khaolam8
            "9kl" -> resource = R.drawable.khaolam9
            "10kl" -> resource = R.drawable.khaolam10
            "1pb" -> resource = R.drawable.phoblack1
            "2pb" -> resource = R.drawable.phoblack2
            "3pb" -> resource = R.drawable.phoblack3
            "4pb" -> resource = R.drawable.phoblack4
            "5pb" -> resource = R.drawable.phoblack5
            "6pb" -> resource = R.drawable.phoblack6
            "7pb" -> resource = R.drawable.phoblack7
            "8pb" -> resource = R.drawable.phoblack8
            "9pb" -> resource = R.drawable.phoblack9
            "10pb" -> resource = R.drawable.phoblack10
            "1pr" -> resource = R.drawable.phored1
            "2pr" -> resource = R.drawable.phored2
            "3pr" -> resource = R.drawable.phored3
            "4pr" -> resource = R.drawable.phored4
            "5pr" -> resource = R.drawable.phored5
            "6pr" -> resource = R.drawable.phored6
            "7pr" -> resource = R.drawable.phored7
            "8pr" -> resource = R.drawable.phored8
            "9pr" -> resource = R.drawable.phored9
            "10pr" -> resource = R.drawable.phored10
            "jfj" -> resource = R.drawable.jackflowerjik
            "jkl" -> resource = R.drawable.jackkhaolam
            "jpb" -> resource = R.drawable.jackphoblack
            "jpr" -> resource = R.drawable.jackphored
            "kfj" -> resource = R.drawable.kingflowerjik
            "kkl" -> resource = R.drawable.kingkhaolam
            "kpb" -> resource = R.drawable.kingphoblack
            "kpr" -> resource = R.drawable.kingphored
            "qfj" -> resource = R.drawable.queenflowerjik
            "qkl" -> resource = R.drawable.queenkhaolam
            "qpb" -> resource = R.drawable.queenphoblack
            "qpr" -> resource = R.drawable.queenphored
            else -> resource = R.drawable.backphai
        }
        return resource
    }

    //TODO getCard_String
    fun getCardString(a: String): dataClassCard {
        var resource = 0
        var value = 0
        when (a) {
            "1fj" -> {
                resource = R.drawable.flowerjik1; value = 1
            }
            "2fj" -> {
                resource = R.drawable.flowerjik2; value = 2
            }
            "3fj" -> {
                resource = R.drawable.flowerjik3; value = 3
            }
            "4fj" -> {
                resource = R.drawable.flowerjik4; value = 4
            }
            "5fj" -> {
                resource = R.drawable.flowerjik5; value = 5
            }
            "6fj" -> {
                resource = R.drawable.flowerjik6; value = 6
            }
            "7fj" -> {
                resource = R.drawable.flowerjik7; value = 7
            }
            "8fj" -> {
                resource = R.drawable.flowerjik8; value = 8
            }
            "9fj" -> {
                resource = R.drawable.flowerjik9; value = 9
            }
            "10fj" -> {
                resource = R.drawable.flowerjik10; value = 10
            }
            "1kl" -> {
                resource = R.drawable.khaolam1; value = 1
            }
            "2kl" -> {
                resource = R.drawable.khaolam2; value = 2
            }
            "3kl" -> {
                resource = R.drawable.khaolam3; value = 3
            }
            "4kl" -> {
                resource = R.drawable.khaolam4; value = 4
            }
            "5kl" -> {
                resource = R.drawable.khaolam5; value = 5
            }
            "6kl" -> {
                resource = R.drawable.khaolam6; value = 6
            }
            "7kl" -> {
                resource = R.drawable.khaolam7; value = 7
            }
            "8kl" -> {
                resource = R.drawable.khaolam8; value = 8
            }
            "9kl" -> {
                resource = R.drawable.khaolam9; value = 9
            }
            "10kl" -> {
                resource = R.drawable.khaolam10; value = 10
            }
            "1pb" -> {
                resource = R.drawable.phoblack1; value = 1
            }
            "2pb" -> {
                resource = R.drawable.phoblack2; value = 2
            }
            "3pb" -> {
                resource = R.drawable.phoblack3; value = 3
            }
            "4pb" -> {
                resource = R.drawable.phoblack4; value = 4
            }
            "5pb" -> {
                resource = R.drawable.phoblack5; value = 5
            }
            "6pb" -> {
                resource = R.drawable.phoblack6; value = 6
            }
            "7pb" -> {
                resource = R.drawable.phoblack7; value = 7
            }
            "8pb" -> {
                resource = R.drawable.phoblack8; value = 8
            }
            "9pb" -> {
                resource = R.drawable.phoblack9; value = 9
            }
            "10pb" -> {
                resource = R.drawable.phoblack10; value = 10
            }
            "1pr" -> {
                resource = R.drawable.phored1; value = 1
            }
            "2pr" -> {
                resource = R.drawable.phored2; value = 2
            }
            "3pr" -> {
                resource = R.drawable.phored3; value = 3
            }
            "4pr" -> {
                resource = R.drawable.phored4; value = 4
            }
            "5pr" -> {
                resource = R.drawable.phored5; value = 5
            }
            "6pr" -> {
                resource = R.drawable.phored6; value = 6
            }
            "7pr" -> {
                resource = R.drawable.phored7; value = 7
            }
            "8pr" -> {
                resource = R.drawable.phored8; value = 8
            }
            "9pr" -> {
                resource = R.drawable.phored9; value = 9
            }
            "10pr" -> {
                resource = R.drawable.phored10; value = 10
            }
            "jfj" -> {
                resource = R.drawable.jackflowerjik; value = 0
            }
            "jkl" -> {
                resource = R.drawable.jackkhaolam; value = 0
            }
            "jpb" -> {
                resource = R.drawable.jackphoblack; value = 0
            }
            "jpr" -> {
                resource = R.drawable.jackphored; value = 0
            }
            "kfj" -> {
                resource = R.drawable.kingflowerjik; value = 0
            }
            "kkl" -> {
                resource = R.drawable.kingkhaolam; value = 0
            }
            "kpb" -> {
                resource = R.drawable.kingphoblack; value = 0
            }
            "kpr" -> {
                resource = R.drawable.kingphored; value = 0
            }
            "qfj" -> {
                resource = R.drawable.queenflowerjik; value = 0
            }
            "qkl" -> {
                resource = R.drawable.queenkhaolam; value = 0
            }
            "qpb" -> {
                resource = R.drawable.queenphoblack; value = 0
            }
            "qpr" -> {
                resource = R.drawable.queenphored; value = 0
            }
            else -> {
                resource = R.drawable.backphai; value = 0
            }
        }
        return dataClassCard(resource, value)
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


