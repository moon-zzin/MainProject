package com.example.mainproject.svplanViewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mainproject.Svplans
import com.example.mainproject.repository.SvplanRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


//SvPlan 데이터 관리 위한 viewmodel
class SvPlanViewModel(private val svplanRepository: SvplanRepository): ViewModel() {

    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("svplans")
    private val _svplanlist = MutableLiveData<MutableList<Svplans>>()
    private val svplanListLiveData = MutableLiveData<List<Svplans>>()
    val svplanlist: LiveData<MutableList<Svplans>> get() = _svplanlist

    //valueEventListener: 추가된 chile를 포함하여 모든 child를 읽어옴 -> 리스트 변화 체크?
    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val svplansList = mutableListOf<Svplans>()

            for (childSnapshot in snapshot.children) {
                try {
                    val svplansItem = childSnapshot.getValue(Svplans::class.java)
                    svplansItem?.let { svplansList.add(it) }
                } catch (e: DatabaseException) {
                    val stringValue = childSnapshot.getValue(String::class.java)
                    if (stringValue != null) {

                        val svplansItem =
                            Svplans(stringValue, stringValue, stringValue, stringValue)
                        svplansList.add(svplansItem)
                    }
                }

            }

            //svplanListLiveData setvalue()는 '메인쓰레드'에서 LiveData의 값 변경
            //postValue()는 백그라운드 쓰레드에서 LiveData의 값 변경 livedata의 값 즉각적 반응 x
            svplanListLiveData.postValue(svplansList)
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }


    // MutableLiveData를 빈 리스트로 초기화
    init {
        _svplanlist.value = mutableListOf()
        startListeningForChanges()
    }

    override fun onCleared() {
        super.onCleared()
        stopListeningForChanges()
    }

    //MutableLiveData 새로운 Svplans Item으로 없데이트
    fun updateSvplanList(svplansItem: Svplans) {
        _svplanlist.value = (_svplanlist.value ?: mutableListOf()).apply {
            add(svplansItem)
        }
        _svplanlist.postValue(_svplanlist.value)
        Log.d("ViewModel", "Updated svplanlist")
    }

    //레포지토리로 svplansItem 전달
    fun addSvplansToRepository(svplansItem: Svplans) {
        svplanRepository.addSvplans(svplansItem)
    }

    fun startListeningForChanges() {
        svplanRepository.startListeningForChanges(valueEventListener)
    }

    fun stopListeningForChanges() {
        svplanRepository.stopListeningForChanges(valueEventListener)
    }

}