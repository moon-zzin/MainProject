package com.example.mainproject

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mainproject.databinding.FragmentAddListBinding
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.viewmodel.AccountViewModel


class addListFragment : Fragment() {
    val viewModel:AccountViewModel by activityViewModels()
    var binding:FragmentAddListBinding?=null
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
        //binding?.calAccount?.setOnDateChangeListener()
        binding?.btnAddAccount?.setOnClickListener{
            val date="??/??"
            val amount=binding?.editAmount?.text.toString()
            val index= viewModel.acclist.value?.numAccounts?.plus(1)
            var category:String?=null
            if(binding?.ckbIncome?.isChecked == true) category="소득"
            if(binding?.ckbExpense?.isChecked == true) category="지출"
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