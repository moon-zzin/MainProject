package com.example.mainproject

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.viewmodel.AccountViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//import com.example.mainproject.viewmodel.MainViewModel


class EntryFragment : Fragment() {
    var binding:FragmentEntryBinding?=null
    //val viewModel: AccountViewModel by viewModels()//초기화 위임
    val viewModel: AccountViewModel by activityViewModels()//상위 개념
    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted = current.format(formatter)
    val acclist= AccList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.txtToday?.text= formatted
        binding?.btnAdd?.setOnClickListener {//버튼설정
            findNavController().navigate(R.id.action_entryFragment_to_addListFragment)
        }
        viewModel.balance.observe(viewLifecycleOwner){
            binding?.txtBalance?.text="잔액: ${viewModel.balance.value}원"
            binding?.txtExpense?.text="지출: ${viewModel.amount.value}원"
            binding?.txtIncome?.text="소득: ${viewModel.amount.value}원"
            view.findViewById<RecyclerView>(R.id.rec_account).apply {//리사이클뷰 설정
                layoutManager =LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                adapter=AccountAdapter(viewModel.acclist.value?.Accounts ?: acclist.Accounts)
            }
        }
        /*binding?.btnAdd?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_addListFragment)
            //네비컨트롤러 반환 후 네비게이트 지정
        }
        binding?.btnAsset?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_myAssetFragment)
        }
        binding?.btnSaving?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_savingPlanListFragment)
        }
        binding?.btnSettings?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_settingsFragment)
        }*/
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EntryFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}