package com.example.mainproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.databinding.FragmentEntryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class savingPlanListFragment : Fragment() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Fragment의 부모뷰 반환(null 처리)
        *//*requireView().findViewById<RecyclerView>(R.id.rv_svplans).apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)  //세로 방향으로 recyclerview 작동
            adapter = SvplansAdapter(svplanlist)
        }*//*
    }*/
    var binding:FragmentEntryBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_saving_plan_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Now, find the RecyclerView and set up its layout manager and adapter
        view.findViewById<RecyclerView>(R.id.rv_svplans).apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = SvplansAdapter(getSampleData())
        }
        val fab: FloatingActionButton = view.findViewById(R.id.btn_add1)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_savingPlanListFragment_to_setSavingPlanFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //목표 금액과 현재 금액 분리하여 추가 하기
    private fun getSampleData(): List<Svplans> {
        return listOf(
            Svplans("스페인 여행", "₩0 / 4,500,000", "D - 10"),
            Svplans("부산 여행", "₩0 / 500,000", "D - 11"),
            Svplans("핸드폰", "₩0 / 1,700,000", "D - 121"),
            Svplans("아이패드", "₩0 / 1,500,000", "D - 1240"),
            Svplans("내집 마련", "₩123,456,789 / 1,034,500,000", "D - 3240"),
            Svplans("내차 사기", "₩0 / 52,500,000", "D - 210"),
            Svplans("스페인 여행", "₩0 / 4,500,000", "D - 10"),
            Svplans("부산 여행", "₩0 / 500,000", "D - 11"),
            Svplans("핸드폰", "₩0 / 1,700,000", "D - 121"),
            Svplans("아이패드", "₩0 / 1,500,000", "D - 1240"),
            Svplans("내집 마련", "₩123,456,789 / 1,034,500,000", "D - 3240"),
            Svplans("내차 사기", "₩0 / 52,500,000", "D - 210")
        )
    }

}
