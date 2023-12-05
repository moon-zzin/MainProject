package com.example.mainproject.viewmodel

import com.example.mainproject.Account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mainproject.AccList
import com.example.mainproject.repository.AccountRepository
class AccountViewModel: ViewModel() {
    private val _balance=MutableLiveData<String>("0")
    private val _amount=MutableLiveData<String>("0")
    val balance : LiveData<String> get()=_balance
    val amount : LiveData<String> get()=_amount
    private  val repository = AccountRepository()
    init{
        repository.observeBal(_balance)
    }
    val init=AccList()
    private val _acclist=MutableLiveData<AccList>(init)
    val acclist:LiveData<AccList> get() = _acclist
    fun addMoney(money:String){
        _balance.value=money
        repository.postBal(_balance.value?:"0")
    }

}
