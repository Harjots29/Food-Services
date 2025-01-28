package com.harjot.foodservicesuser.fragments.eventsection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.databinding.FragmentEventDateBottomSheetBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventDateBottomSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventDateBottomSheet : BottomSheetDialogFragment() {
    val binding by lazy {
        FragmentEventDateBottomSheetBinding.inflate(layoutInflater)
    }
    private var onDateSelectedListener: OnDateSelectedListener? = null
    // Define an interface to send the selected date back to the parent fragment
    interface OnDateSelectedListener {
        fun onDateSelected(date: String)
    }
    // Set the listener
    fun setOnDateSelectedListener(listener: OnDateSelectedListener) {
        onDateSelectedListener = listener
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)

        val calendar = Calendar.getInstance()
        calendarView.minDate = calendar.timeInMillis

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Create a formatted date string
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar.time)
            // Pass the selected date to the listener
            onDateSelectedListener?.onDateSelected(formattedDate)
            dismiss() // Close the bottom sheet
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventDateBottomSheet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventDateBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}