package com.example.mainproject.repository

import com.example.mainproject.Svplans
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SvplanRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("svplans")

    fun addSvplans(svplansItem: Svplans) {
        databaseReference.push().setValue(svplansItem)
    }


}
