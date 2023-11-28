package com.example.mainproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.svplanViewmodel.SvPlanViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class savingPlanListFragment : Fragment() {

    var binding:FragmentEntryBinding? = null
    private val svplanlist = mutableListOf<Svplans>()
    private val viewModel: SvPlanViewModel by viewModels()

    //onCreateView함수 지우면 findViewbyId함수 사용 x
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_saving_plan_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Now, find the RecyclerView and set up its layout manager and adapter
        view.findViewById<RecyclerView>(R.id.rv_svplans).apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = SvplansAdapter(svplanlist)
        }

        val name = arguments?.getString("name", "")
        val setBudget = arguments?.getString("setBudget", "0") ?: 0
        val curBudget = arguments?.getString("curBudget", "0") ?: 0
        val selectedDate = arguments?.getLong("selectedDate", 0L) ?: 0


        // Create a new Svplans item
        val svplansItem = name?.let {
            Svplans(
                name = it,
                curBudget = curBudget.toString(),
                setBudget = setBudget.toString(),
                dday = calculateDday(selectedDate, currentDate = System.currentTimeMillis()).toString()
            )
        }

        if (svplansItem != null) {
            svplanlist.add(svplansItem)
            viewModel.updateSvplanList(svplansItem)
            view.findViewById<RecyclerView>(R.id.rv_svplans).adapter?.notifyDataSetChanged()
        }

        //FloatingActionButton 클릭 시 setSavingPlanFragment로 넘어감
        val add1: FloatingActionButton = view.findViewById(R.id.btn_add1)
        add1.setOnClickListener {
            findNavController().navigate(R.id.action_savingPlanListFragment_to_setSavingPlanFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    //현재금액 수정 기능 추가, RecyclerView item setonclicklistener 설정
    //D-day카운트, display 기능 추가
    //목표 금액과 현재 금액 분리하여 추가 하기

    /*fun updateRecyclerView(name: String, setBudget: String, curBudget: String, selectedDate: Long) {
        val svplansItem = Svplans(
            name = name,
            curBudget = curBudget.toString(),
            setBudget = setBudget.toString(),
            dday = calculateDday(selectedDate).toString()
        )
        svplanlist.add(svplansItem)
        viewModel.updateSvplanList(svplansItem)
        view?.findViewById<RecyclerView>(R.id.rv_svplans)?.adapter?.notifyDataSetChanged()
        *//*svplanlist.add(svplansItem)
        view?.findViewById<RecyclerView>(R.id.rv_svplans)?.adapter?.notifyDataSetChanged()*//*
    }*/
}

/*private fun getSampleData(): List<Svplans> {
        return listOf(
            Svplans("스페인 여행", "₩0 /", "1,700,000","D - 10"),
            Svplans("부산 여행", "₩0 /", "1,700,000","D - 11"),
            Svplans("핸드폰", "₩0 / ", "1,700,000","D - 121"),
            Svplans("아이패드", "₩0 / ", "1,700,000", "D - 1240"),
            Svplans("내집 마련", "₩123,456,789 / ", "1,034,500,000","D - 3240"),
            Svplans("내차 사기", "₩0 / ", "52,500,000 ","D - 210"),
            Svplans("스페인 여행", "₩0 / ", "1,700,000","D - 10"),
            Svplans("부산 여행", "₩0 / ", "1,700,000","D - 11"),
            Svplans("핸드폰", "₩0 / ", "1,700,000","D - 121"),
            Svplans("아이패드", "₩0 / ", "1,700,000","D - 1240"),
            Svplans("내집 마련", "₩123,456,789 / ", "1,034,500,000","D - 3240"),
            Svplans("내차 사기", "₩0 / ", "52,500,000","D - 210")
        )
    }*/