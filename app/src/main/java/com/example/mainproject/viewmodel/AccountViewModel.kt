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
    val balance : LiveData<String> get()=_balance
    val income : LiveData<String> get()=_income
    val expense : LiveData<String> get()=_expense
    private  val repository=AccountRepository()
    init{
        repository.observeBal(_balance)
    }
    val init=AccList()
    private val _acclist=MutableLiveData<AccList>(init)
    val acclist:LiveData<AccList> get() = _acclist
    fun addMoney(money:String,incType:Boolean?,expType:Boolean?){
        if(expType==true) _expense.value=(_expense.value?.toInt()?.plus(money.toInt())).toString()
        if(incType==true) _income.value=(_income.value?.toInt()?.plus(money.toInt())).toString()
        //repository.postBal(_balance.value?:"0")
    }

}
