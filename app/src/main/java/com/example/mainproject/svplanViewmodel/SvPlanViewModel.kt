package com.example.mainproject.svplanViewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mainproject.Svplans
import com.example.mainproject.repository.SvplanRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


//SvPlan 데이터 관리 위한 viewmodel
class SvPlanViewModel(private val svplanRepository: SvplanRepository): ViewModel() {

    private val datatbaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("svplans")
    private val _svplanlist = MutableLiveData<MutableList<Svplans>>()
    val svplanlist: LiveData<MutableList<Svplans>> get() = _svplanlist

    // MutableLiveData를 빈 리스트로 초기화
    init {
        _svplanlist.value = mutableListOf()
    }

    //MutableLiveData 새로운 Svplans Item으로 없데이트
    fun updateSvplanList(svplansItem: Svplans) {
        /*val currentList = _svplanlist.value ?: mutableListOf()
        currentList.add(svplansItem)
        _svplanlist.value = currentList

        Log.d("ViewModel", "Updated svplanlist: $currentList")*/
        _svplanlist.postValue(_svplanlist.value?.apply { add(svplansItem) } ?: mutableListOf(svplansItem))
    }


    fun addSvplansToRepository(svplansItem: Svplans) {
        svplanRepository.addSvplans(svplansItem)
    }
}