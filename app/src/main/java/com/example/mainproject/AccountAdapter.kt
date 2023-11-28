package com.example.mainproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.databinding.EntryAccountBinding

class AccountAdapter(val accounts: Array<Account?>) : RecyclerView.Adapter<AccountAdapter.Holder>(){//모델을 넘겨줌
    class Holder(val binding: EntryAccountBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(account: Account?){
            binding.txtCategory.text=account?.category
            binding.txtDate.text=account?.date.toString()
            binding.txtAmount.text=account?.amount
            binding.txtIndex.text= account?.index.toString()
        }
    }
    fun cntItem(accouts:Array<Account?>): Int {
        var count:Int=0
        while (accouts[count]!=null){
            count+=1
        }
        return count
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {//뷰홀더(한칸)
        val binding=EntryAccountBinding.inflate(LayoutInflater.from(parent.context))//홀더마다 만들어야 함
        return Holder(binding)
    }
    override fun getItemCount()=cntItem(accounts)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(accounts[position])
    }
}