package com.example.mainproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SvplansAdapter(val svplanlist: List<Svplans>) : RecyclerView.Adapter<SvplansAdapter.CustomViewHolder>() {

    //목표 금액과 현재 금액 분리하여 추가 하기
    //ViewHolder 생성자
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.tv_svplan_name)     //플랜 이름
        val setBudget = itemView.findViewById<TextView>(R.id.tv_svplan_setbudget) //예산
        val curBudget = itemView.findViewById<TextView>(R.id.tv_svplan_curbudget)
        //목표 금액과 현재 금액 분리하여 추가 하기 set_budget(목표 예산), cur_budget(현재 예산)
        val dday = itemView.findViewById<TextView>(R.id.tv_svplan_dday)     //디데이
    }

    //svplanlist_item 가져옴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SvplansAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.svplanlist_item, parent, false)  //parent.context : Activity에 대한 정보 가져옴

        return CustomViewHolder(view)    //onCreateViewHolder의 view 변수가 CustomViewHolder 생성자의 인수로 전달, itemView-> view
    }

    //onCreateViewHolder로 만들어진 view 연결
    override fun onBindViewHolder(holder: SvplansAdapter.CustomViewHolder, position: Int) {
        holder.name.text = svplanlist.get(position).name
        holder.curBudget.text = svplanlist.get(position).curBudget
        holder.setBudget.text = svplanlist.get(position).setBudget
        holder.dday.text = svplanlist.get(position).dday
    }

    override fun getItemCount(): Int = svplanlist.size
}

