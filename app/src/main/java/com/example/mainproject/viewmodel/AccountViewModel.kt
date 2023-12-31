package com.example.mainproject.viewmodel

import com.example.mainproject.Account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mainproject.AccList
import com.example.mainproject.repository.AccountRepository
class AccountViewModel: ViewModel() {
    private val _balance=MutableLiveData<String>("0")
    private val _income=MutableLiveData<String>("0")
    private val _expense=MutableLiveData<String>("0")
    val balance : LiveData<String> get()=_balance//잔액
    val income : LiveData<String> get()=_income
    val expense : LiveData<String> get()=_expense
    //private  val repository=AccountRepository()
    //init{
    //    repository.observeBal(_balance)
    //}
    val init=AccList()
    private val _acclist=MutableLiveData<AccList>(init)
    val acclist:LiveData<AccList> get() = _acclist
    fun addMoney(money:Int,isType:Boolean?){
        if(isType==true) {
            _expense.value=(_expense.value?.toInt()?.plus(money)).toString()
        } else if(isType==false) {
            _income.value=(_income.value?.toInt()?.plus(money)).toString()
        }
        //repository.postBal(_balance.value?:"0")
    }

}
