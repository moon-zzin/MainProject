package com.example.mainproject.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.mainproject.Svplans
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class SvplanRepository(context: Context) {
    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("svplans")
    private val svplanListLiveData = MutableLiveData<List<Svplans>>()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("svplansPrefs", Context.MODE_PRIVATE)

    //valueEventListener: 추가된 chiled를 포함하여 모든 child를 읽어옴 -> 리스트 변화 체크?
    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val svplansList = mutableListOf<Svplans>()

            for (childSnapshot in snapshot.children) {
                try{
                    val svplansItem = childSnapshot.getValue(Svplans::class.java)
                    svplansItem?.let { svplansList.add(it) }
                } catch (e: DatabaseException) {
                    val stringValue = childSnapshot.getValue(String::class.java)
                    if (stringValue != null) {
                        val svplansItem = Svplans(stringValue, stringValue, stringValue, stringValue)
                        svplansList.add(svplansItem)
                    }
                }

            }

            // Update the local data
            svplanListLiveData.postValue(svplansList)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle the error if needed
        }
    }
    fun addSvplans(svplansItem: Svplans) {
        databaseRef.push().setValue(svplansItem)
        saveSvplansToLocal(svplansItem)
    }

    fun startListeningForChanges(valueEventListener: ValueEventListener) {
        databaseRef.addValueEventListener(valueEventListener)
    }

    fun stopListeningForChanges(valueEventListener: ValueEventListener) {
        databaseRef.removeEventListener(valueEventListener)
    }

    //로컬에 svplanlist저장 근데 작동안됨;;
    private fun saveSvplansToLocal(svplansItem: Svplans) {
        // 남아있는 데이터 읽어옴
        val svplansList = getSvplansFromLocal()
        // Add the new item
        svplansList.add(svplansItem)

        // sharedPreferences에 "svplans"키에 Json string으로 저장
        val editor = sharedPreferences.edit()
        editor.putString("svplans", Gson().toJson(svplansList))
        editor.apply()
    }
    //svplanlist읽어옴
    private fun getSvplansFromLocal(): MutableList<Svplans> {
        val svplansList = mutableListOf<Svplans>()

        // Load data from local SharedPreferences
        val svplansJson = sharedPreferences.getString("svplans", null)
        svplansJson?.let {
            val svplans = Gson().fromJson(it, Array<Svplans>::class.java)
            svplansList.addAll(svplans)
        }

        return svplansList
    }

}
