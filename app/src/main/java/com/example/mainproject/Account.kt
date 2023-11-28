package com.example.mainproject;

import java.time.temporal.TemporalAmount
import java.util.Date

class Account(val category:String?, val date:String?, val amount: String?, val index:Int?)
class AccList {
    val Accounts = Array<Account?>(100) {_ -> null}
    var numAccounts = 0
    fun addAcc(c: Account?) {
        Accounts[numAccounts] = c;
        if(c!=null) numAccounts += 1;
    }
    fun removeAcc(num: Int) {
        Accounts[num] = null;
    }
}


