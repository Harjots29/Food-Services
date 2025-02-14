package com.harjot.foodservicesuser.fragments.eventsection

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.adapters.CategoryAdapter
import com.harjot.foodservicesuser.adapters.EventListAdapter
import com.harjot.foodservicesuser.databinding.FragmentAddEventsBinding
import com.harjot.foodservicesuser.models.CategoryModel
import com.harjot.foodservicesuser.models.EventsModel
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
    var  categoryList = ArrayList<CategoryModel>()
    lateinit var categoryAdapter: CategoryAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScreenBottomNav = activity as MainScreenBottomNav

        categoryAdapter = CategoryAdapter(categoryList)
        binding.rvCategoryList.layoutManager = GridLayoutManager(mainScreenBottomNav,2)
        binding.rvCategoryList.adapter = categoryAdapter

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

        val eventItems = listOf("Birthday", "Anniversary", "Wedding", "Other")
        val eventAdapter = ArrayAdapter(mainScreenBottomNav, android.R.layout.simple_spinner_item, eventItems)
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.EventSpinner.adapter = eventAdapter

        binding.EventSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Birthday" -> {
                        // Handle "Birthday" option
                    }
                    "Anniversary" -> {
                        // Handle "Anniversary" option
                    }
                    "Wedding" -> {
                        // Handle "Wedding" option
                    }
                    "Other" -> {
                        // Handle "Other" option
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected
            }
        }

        val categoryItems = listOf("Custom", "Starters", "Main Course", "Dessert")
        val categorySpinnerAdapter = ArrayAdapter(mainScreenBottomNav, android.R.layout.simple_spinner_item, categoryItems)
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.CategorySpinner.adapter = categorySpinnerAdapter

        binding.CategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Custom" -> {
                        // Handle "Custom" option
                        categoryList.clear()
                        categoryList.add(
                            CategoryModel(
                                name = "Pizza",
                                check = false
                            )
                        )
                        categoryList.add(
                            CategoryModel(
                                name = "Burger",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Sandwich",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Dal Makhni",
                                check = false
                            )
                        )
                        categoryList.add(
                            CategoryModel(
                                name = "Shahi Paneer",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Mix Veg",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Chapati",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Ice Cream",
                                check = false
                            )
                        )
                        categoryList.add(
                            CategoryModel(
                                name = "Ras Malai",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Jalebi",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Gulab Jamun",
                                check = false
                            ))
                        categoryAdapter.notifyDataSetChanged()
                    }
                    "Starters" -> {
                        // Handle "Starters" option
                        categoryList.clear()
                        categoryList.add(
                            CategoryModel(
                                name = "Pizza",
                                check = false
                            )
                        )
                        categoryList.add(
                            CategoryModel(
                                name = "Burger",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Sandwich",
                                check = false
                            ))
                        categoryAdapter.notifyDataSetChanged()
                    }
                    "Main Course" -> {
                        // Handle "Main Course" option
                        categoryList.clear()
                        categoryList.add(
                            CategoryModel(
                                name = "Dal Makhni",
                                check = false
                            )
                        )
                        categoryList.add(
                            CategoryModel(
                                name = "Shahi Paneer",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Mix Veg",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Chapati",
                                check = false
                            ))
                        categoryAdapter.notifyDataSetChanged()
                    }
                    "Dessert" -> {
                        // Handle "Dessert" option
                        categoryList.clear()
                        categoryList.add(
                            CategoryModel(
                                name = "Ice Cream",
                                check = false
                            )
                        )
                        categoryList.add(
                            CategoryModel(
                                name = "Ras Malai",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Jalebi",
                                check = false
                            ))
                        categoryList.add(
                            CategoryModel(
                                name = "Gulab Jamun",
                                check = false
                            ))
                        categoryAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected
            }
        }

        binding.btnAddEvent.setOnClickListener {
            if(binding.tvTime.text == "Select Time"){
                val snackbar = Snackbar.make(it, "You have not selected the Time!", Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("Ok") { snackbar.dismiss() }

                val snackbarView = snackbar.view
                snackbarView.setBackgroundColor(Color.TRANSPARENT)

                // Custom background of snackbar with rounded corners
                val background = GradientDrawable()
                background.cornerRadius = 50f

                // Apply background
                snackbarView.background = background
                snackbarView.elevation = 20f

                val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(20, 0, 20, 100) // Left, Top, Right, Bottom Margin
                snackbarView.layoutParams = params

                var textView = snackbarView
                    .findViewById<TextView>(
                        com.google.android.material.R.id.snackbar_text
                    )
                var actionText = snackbarView
                    .findViewById<TextView>(
                        com.google.android.material.R.id.snackbar_action
                    )
                textView.textSize = 18f

                actionText.textSize = 18f

                snackbar.setBackgroundTint(resources.getColor(R.color.red))
                snackbar.setTextColor(Color.WHITE)
                snackbar.setActionTextColor(Color.WHITE)

                    .show()
            }
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