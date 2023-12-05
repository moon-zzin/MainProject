package com.example.mainproject.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class AccountRepository {
    val database = FirebaseDatabase.getInstance()
    val balRef = database.getReference("balance")
    fun observeBal(bal:MutableLiveData<String>){
        balRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                bal.postValue(snapshot.value.toString())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun postBal(newValue:String){
        balRef.setValue(newValue)
    }
}