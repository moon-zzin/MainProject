package com.example.mainproject.svplanViewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mainproject.Svplans

//SvPlan 데이터 관리 위한 viewmodel
class SvPlanViewModel: ViewModel() {
    private val _svplanlist = MutableLiveData<MutableList<Svplans>>()
    val svplanlist: LiveData<MutableList<Svplans>> get() = _svplanlist

    // MutableLiveData를 빈 리스트로 초기화
    init {
        _svplanlist.value = mutableListOf()
    }

    // Function to update the MutableLiveData with a new Svplans item
    fun updateSvplanList(svplansItem: Svplans) {
        val currentList = _svplanlist.value ?: mutableListOf()
        currentList.add(svplansItem)
        _svplanlist.value = currentList
    }
}