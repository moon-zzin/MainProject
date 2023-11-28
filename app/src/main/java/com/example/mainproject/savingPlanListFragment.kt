package com.example.mainproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.svplanViewmodel.SvPlanViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
//현재금액 수정 기능 추가, RecyclerView item onclicklistener 설정
//dDay = -1 되면 아이템 삭제
class savingPlanListFragment : Fragment() {

    private var binding: FragmentEntryBinding? = null
    private val svplanlist = mutableListOf<Svplans>()
    private val viewModel: SvPlanViewModel by viewModels({requireActivity()})
    private val adapter = SvplansAdapter(svplanlist)


    //onCreateView함수 지우면 findViewbyId함수 사용 x
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_saving_plan_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_svplans)

        //RecyclerView 방향 설정, Svplansadapter로 svplanlist 전달
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
        }

        //svplanlist observer설정
        viewModel.svplanlist.observe(viewLifecycleOwner) { svplans ->
            svplanlist.clear()
            svplanlist.addAll(svplans)
            //RecyclerView Adapter 업데이트
            //view.findViewById<RecyclerView>(R.id.rv_svplans).adapter?.notifyDataSetChanged()
            adapter.notifyDataSetChanged()
            manageRecyclerViewVisibility(svplanlist.isEmpty())

            Log.d("Fragment", "Adapter notified of data change")
        }

        //FloatingActionButton 클릭 시 setSavingPlanFragment로 넘어감
        val add1: FloatingActionButton = view.findViewById(R.id.btn_add1)
        add1.setOnClickListener {
            findNavController().navigate(R.id.action_savingPlanListFragment_to_setSavingPlanFragment)
        }
    }

    //텍스트뷰 출력상태 제어
    private fun manageRecyclerViewVisibility(isEmpty: Boolean) {

        val recyclerView: RecyclerView = requireView().findViewById(R.id.rv_svplans)
        val tvNoPlan: TextView = requireView().findViewById(R.id.tv_noplan)

        if (svplanlist.isEmpty()) {
            recyclerView.visibility = View.GONE
            tvNoPlan.visibility = View.VISIBLE
        }
        else {
            recyclerView.visibility = View.VISIBLE
            tvNoPlan.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
