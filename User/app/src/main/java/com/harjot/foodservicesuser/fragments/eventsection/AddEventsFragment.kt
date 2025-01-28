package com.harjot.foodservicesuser.fragments.eventsection

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.databinding.FragmentAddEventsBinding
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddEventsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEventsFragment : Fragment() {
    val binding by lazy {
        FragmentAddEventsBinding.inflate(layoutInflater)
    }
    lateinit var mainScreenBottomNav: MainScreenBottomNav
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScreenBottomNav = activity as MainScreenBottomNav
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val today = LocalDate.now()

        val monthDayText = binding.monthDay
        val monthAndDay = today.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + today.dayOfMonth
        monthDayText.text = monthAndDay

        val daysOfWeek = listOf("mon", "tue", "wed", "thu", "fri", "sat", "sun")
        val dates = (0..6).map { today.plusDays(it.toLong()).dayOfMonth.toString() }

        val dayViews = listOf(
           binding.day1, binding.day2, binding.day3, binding.day4, binding.day5, binding.day6, binding.day7
        )
        for (i in dayViews.indices) {
            dayViews[i].text = daysOfWeek[i]
        }

        val dateViews = listOf(
            binding.date1, binding.date2, binding.date3, binding.date4, binding.date5, binding.date6, binding.date7
        )
        for (i in dateViews.indices) {
            val dateView = dateViews[i]
            dateView.text = dates[i]

            dateView.setOnClickListener {
                val selectedDate = today.plusDays(i.toLong())
                val selectedMonthAndDay = selectedDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + selectedDate.dayOfMonth

                monthDayText.text = selectedMonthAndDay

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivSearchDate.setOnClickListener {
            val bottomSheetDateFragment = EventDateBottomSheet()
            bottomSheetDateFragment.show(parentFragmentManager, bottomSheetDateFragment.tag)
            bottomSheetDateFragment.setOnDateSelectedListener(object : EventDateBottomSheet.OnDateSelectedListener {
                override fun onDateSelected(date: String) {
                    // Handle the selected date (e.g., update a TextView)
                    val selectedDateTextView = binding.monthDay
                    selectedDateTextView.text = date
                }
            })
        }
        binding.cvSelectTime.setOnClickListener {
            val bottomSheetTimeFragment = EventTimeBottomSheet()
            bottomSheetTimeFragment.show(parentFragmentManager, bottomSheetTimeFragment.tag)
            bottomSheetTimeFragment.setOnTimeSelectedListener(object : EventTimeBottomSheet.OnTimeSelectedListener {
                override fun onTimeSelected(time: String) {
                    // Handle the selected time (e.g., update a TextView)
                    val selectedTimeTextView = binding.tvTime
                    selectedTimeTextView.text = time
                }
            })
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddEventsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddEventsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}