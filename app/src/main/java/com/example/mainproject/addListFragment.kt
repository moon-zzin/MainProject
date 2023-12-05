package com.example.mainproject

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.activityViewModels
import com.example.mainproject.databinding.FragmentAddListBinding
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.viewmodel.AccountViewModel
import java.util.Calendar


class addListFragment : Fragment() {
    val viewModel:AccountViewModel by activityViewModels()
    var binding:FragmentAddListBinding?=null
    private var accountDate: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddListBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var date:String?=null
        val calaccount: CalendarView? = binding?.calAccount
        calaccount?.setOnDateChangeListener{calaccount,y,m,d->
            m.plus(1)
            date="$y.$m.$d"
        }
        binding?.btnAddAccount?.setOnClickListener{
            val amount=binding?.editAmount?.text.toString()
            val index= viewModel.acclist.value?.numAccounts?.plus(1)
            var category:String?=null
            var isInc=binding?.ckbIncome?.isChecked
            var isExp=binding?.ckbExpense?.isChecked
            if(isInc==true) category="소득"
            if(isExp == true) category="지출"
            viewModel.addMoney(amount,isInc,isExp)
            viewModel.acclist.value?.addAcc(Account(category, date, amount, index))
            //viewModel.addMoney(binding?.editAmount?.text.toString())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}