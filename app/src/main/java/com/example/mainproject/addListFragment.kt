package com.example.mainproject

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mainproject.databinding.FragmentAddListBinding
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.viewmodel.AccountViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items=resources.getStringArray(R.array.items)
        //val catAdapter = this.context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        var category:String?=null
        var isType:Boolean?=null
        //binding?.spinnerCate?.adapter = catAdapter
        val catAdapter = this.context?.let {
            object : ArrayAdapter<String>(
                it,
                R.layout.item_spinner
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val v = super.getView(position, convertView, parent)
                    if (position == count) {
                        (v.findViewById<View>(R.id.tvItemSpinner) as TextView).text = ""
                        (v.findViewById<View>(R.id.tvItemSpinner) as TextView).hint = getItem(count)
                    }
                    return v
                }
                override fun getCount(): Int {
                    return super.getCount()-1
                }
            }
        }
        catAdapter?.addAll(items.toMutableList())
        //catAdapter?.add("카테고리 지정")
        binding?.spinnerCate?.adapter = catAdapter
        binding?.spinnerCate?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                category=items[position]
                isType = position >= 2
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
        }
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val formatted = current.format(formatter)
        var date=formatted
        val calaccount: CalendarView? = binding?.calAccount
        calaccount?.setOnDateChangeListener{calaccount,y,m,d->
            date="$y.${m+1}.$d"
        }
        binding?.btnAddAccount?.setOnClickListener{
            val amount=binding?.editAmount?.text.toString()
            if(amount.isEmpty()) Toast.makeText(requireActivity(), "금액을 입력해 주세요", Toast.LENGTH_SHORT).show()
            else if(category?.isEmpty()==true) Toast.makeText(requireActivity(), "카테고리를 지정해 주세요", Toast.LENGTH_SHORT).show()
            //else if(date.isEmpty()) Toast.makeText(requireActivity(), "날짜를 지정해 주세요", Toast.LENGTH_SHORT).show()
            else {
                viewModel.addMoney(amount.toInt(), isType)
                viewModel.acclist.value?.addAcc(Account(category, date, amount))
                findNavController().navigate(R.id.action_addListFragment_to_entryFragment)
            }
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
