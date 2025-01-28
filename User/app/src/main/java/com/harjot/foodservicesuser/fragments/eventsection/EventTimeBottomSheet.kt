package com.harjot.foodservicesuser.fragments.eventsection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.databinding.FragmentEventTimeBottomSheetBinding
import com.harjot.foodservicesuser.fragments.eventsection.EventDateBottomSheet.OnDateSelectedListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventTimeBottomSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventTimeBottomSheet : BottomSheetDialogFragment() {
    val binding by lazy {
        FragmentEventTimeBottomSheetBinding.inflate(layoutInflater)
    }
    private var onTimeSelectedListener: OnTimeSelectedListener? = null
    // Define an interface to send the selected date back to the parent fragment
    interface OnTimeSelectedListener {
        fun onTimeSelected(time: String)
    }
    // Set the listener
    fun setOnTimeSelectedListener(listener: OnTimeSelectedListener) {
        onTimeSelectedListener = listener
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
        val hourPicker = binding.hourPicker
        val minutePicker = binding.minutePicker
        val amPmPicker = binding.amPmPicker
        val confirmButton = binding.confirmButton

        // Configure Hour Picker
        hourPicker.minValue = 1
        hourPicker.maxValue = 12
        hourPicker.wrapSelectorWheel = true

        // Configure Minute Picker
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.wrapSelectorWheel = true

        // Configure AM/PM Picker
        amPmPicker.minValue = 0
        amPmPicker.maxValue = 1
        amPmPicker.displayedValues = arrayOf("AM", "PM")
        amPmPicker.wrapSelectorWheel = true

        confirmButton.setOnClickListener {
            val selectedHour = hourPicker.value
            val selectedMinute = minutePicker.value
            val amPm = if (amPmPicker.value == 0) "AM" else "PM"

            val formattedTime = String.format("%02d:%02d %s", selectedHour, selectedMinute, amPm)
            onTimeSelectedListener?.onTimeSelected(formattedTime)
            dismiss()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventTimeBottomSheet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventTimeBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}