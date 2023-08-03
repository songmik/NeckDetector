package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mj.neckdetector.databinding.FragmentCareBinding
import com.mj.neckdetector.utils.SharedPreferencesManager
import java.util.Calendar

class CareFragment : Fragment() {

    private var _binding: FragmentCareBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setCalendarView()

        binding.calender.minDate = Calendar.getInstance().timeInMillis


        // 닉네임 보여주기
        val myNickName = SharedPreferencesManager.getNickname()

        binding.nicknameTV.text = "$myNickName 님"


        // 설정화면으로 돌아가기
        binding.backIV.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

//    private fun setCalendarView() {
//        binding.calender.dayBinder = object : MonthDayBinder<DayViewContainer> {
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun bind(container: DayViewContainer, data: CalendarDay) {
//                container.textView.text = data.date.dayOfMonth.toString()
//            }
//
//            override fun create(view: View) = DayViewContainer(view)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}