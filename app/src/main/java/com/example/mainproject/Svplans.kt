package com.example.mainproject

//목표 금액과 현재 금액 분리하여 추가 하기 set_budget(목표 예산), cur_budget(현재 예산)
data class Svplans(
    val name: String = ""
, val curBudget: String = ""
, val setBudget: String = ""
, val dDay: String = "") {
}