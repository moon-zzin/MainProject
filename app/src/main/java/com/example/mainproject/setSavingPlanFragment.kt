package com.example.mainproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainproject.databinding.FragmentEntryBinding
import com.example.mainproject.svplanViewmodel.SvPlanViewModel
import java.util.Calendar

//(추가)리사이클러뷰 아이템클릭 이벤트 -> 수정
class setSavingPlanFragment : Fragment() {

    var binding: FragmentEntryBinding? = null
    private val viewModel: SvPlanViewModel by viewModels({ requireActivity() })
    private var selectedDate: Long = 0

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_saving_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //날짜 정보 받는 setOnDateChageListener 설정 *clickListener 외부에 설정해야 날짜 한번에 얻음(왜인지는 잘 모르겠음...)
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calender = Calendar.getInstance()
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, month)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            selectedDate = calender.timeInMillis
        }
        val currentDate = System.currentTimeMillis()

        //플랜 추가 버튼(플랜이름, 예산, 날짜 입력받아 savgngPlanListFragment에 recyclerview 아이템 추가)
        val btnAddSvplan: Button = view.findViewById(R.id.btn_mod_svplan)
        btnAddSvplan.setOnClickListener {
            //플랜 이름
            val name = view.findViewById<EditText>(R.id.txt_svplan_name)?.text.toString()
            //현재 예산(처음엔 0으로 초기화)
            val curBudgetTextView = view.findViewById<TextView>(R.id.tv_svplan_curbudget)
            val curBudget =
                curBudgetTextView?.text?.toString() ?: "0" //curBudgetTextview가 Null이면 default 값으로 0
            //목표 예산
            val setBudget = view.findViewById<EditText>(R.id.txt_svplan_budget)?.text.toString()

            //버튼이 클릭 된 후 값 읽음, 세 값이 비어있지 않을때만 navigate동작
            if (name.isNotEmpty() && setBudget.isNotEmpty() && calculateDday(
                    selectedDate,
                    currentDate
                ).toInt() > 0
            ) {
                //fragement가 처음 생성되면 savedInstanceState가 null
                if (savedInstanceState == null) {
                    val svplansItem = Svplans(
                        name = name,
                        curBudget = curBudget,
                        setBudget = setBudget,
                        dDay = calculateDday(selectedDate, currentDate)
                    )
                    viewModel.updateSvplanList(svplansItem)
                    viewModel.addSvplansToRepository(
                        Svplans(
                            name,
                            curBudget,
                            setBudget,
                            dDay = calculateDday(selectedDate, currentDate)
                        )
                    )
                }

                val bundle = Bundle().apply {//apply는 run에 대응하지만 run과 다르게 수신 객체를 반환
                    putString("name", name)
                    putString("curBudget", curBudget)
                    putString("setBudget", setBudget)
                    putLong("selectedDate", selectedDate)
                }


                //정보 전달 받아서 RecylcerView Item 생성 -> savingPlanListFragment
                findNavController().navigate(
                    R.id.action_setSavingPlanFragment_to_savingPlanListFragment, bundle
                )
            }
            //이름이 입력 안된경우  toast message
            else if (name.isEmpty()) {
                //requireActivity: 액티비티가 null이면 IllegalStateException throw함, nullable 객체 return 가능 activity!!는 NullPointerException throw
                Toast.makeText(requireActivity(), "플랜 이름을 입력해 주세요", Toast.LENGTH_SHORT).show()
                Log.d("Debug", "plan name is empty")
            }
            //예산이 입력 안된경우 toast message
            else if (name.isNotEmpty() && setBudget.isEmpty()) {
                Toast.makeText(requireActivity(), "목표 금액을 입력해 주세요", Toast.LENGTH_SHORT).show()
                Log.d("Debug", "setBudget is empty")
            }
            //잘못돤 날짜 선택된 경우 toast message
            else if (name.isNotEmpty() && setBudget.isNotEmpty() && selectedDate <= currentDate) {
                Toast.makeText(requireActivity(), "유효한 날짜를 선택해 주세요", Toast.LENGTH_SHORT).show()
                Log.d("Debug", "wrong selectedDate")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //calculateDday 정의 (디데이 계산 함수)
    private fun calculateDday(selectedDate: Long, currentDate: Long): String =
        ((selectedDate - currentDate) / (24 * 60 * 60 * 1000)).toString()

}
