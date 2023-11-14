package com.example.mainproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.viewmodel.MainViewModel


class EntryFragment : Fragment() {
    var binding:FragmentEntryBinding?=null
    val viewModel:MainViewModel by activityViewModels()//뷰모델스함수한테 위임

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnAdd?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_addListFragment)
        }
        viewModel.mbti.observe(viewLifecycleOwner){
            binding
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