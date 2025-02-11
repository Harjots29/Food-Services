package com.harjot.foodservicesuser.fragments.eventsection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.adapters.EventListAdapter
import com.harjot.foodservicesuser.databinding.FragmentEventListScreenBinding
import com.harjot.foodservicesuser.models.EventsModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventListScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventListScreen : Fragment() {
    val binding by lazy {
        FragmentEventListScreenBinding.inflate(layoutInflater)
    }
    lateinit var mainScreenBottomNav: MainScreenBottomNav
    var  eventList = ArrayList<EventsModel>()
    lateinit var eventListAdapter: EventListAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScreenBottomNav = activity as MainScreenBottomNav

        mainScreenBottomNav.binding.fabEventAdd.visibility = View.VISIBLE

        eventListAdapter = EventListAdapter(eventList)
        binding.rvEventList.layoutManager = LinearLayoutManager(mainScreenBottomNav)
        binding.rvEventList.adapter = eventListAdapter

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
        eventList.clear()
        eventList.add(
            EventsModel(
            eventName = "Birthday",
            price = "500",
            date = "25-Jan-2025",
            time = "6:00 pm",
            venue = "Kartarpur"
        ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))
        eventList.add(
            EventsModel(
                eventName = "Birthday",
                price = "500",
                date = "25-Jan-2025",
                time = "6:00 pm",
                venue = "Kartarpur"
            ))


        eventListAdapter.notifyDataSetChanged()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainScreenBottomNav.binding.fabEventAdd.setOnClickListener {
            mainScreenBottomNav.navController.navigate(R.id.addEventsFragment)
            mainScreenBottomNav.binding.fabEventAdd.visibility = View.GONE
        }
        binding.rvEventList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val btnHome = mainScreenBottomNav.binding.btnHome
                val btnEvent = mainScreenBottomNav.binding.btnEvent
                val btnInfo = mainScreenBottomNav.binding.btnInfo

                if (dy > 0) { // Scroll Down
                    btnHome.animate().alpha(0f).setDuration(200).withEndAction {
                        btnHome.visibility = View.GONE
                    }
                    btnEvent.animate().alpha(0f).setDuration(200).withEndAction {
                        btnEvent.visibility = View.GONE
                    }
                    btnInfo.animate().alpha(0f).setDuration(200).withEndAction {
                        btnInfo.visibility = View.GONE
                    }
                } else if (dy < 0) { // Scroll Up
                    btnHome.visibility = View.VISIBLE
                    btnHome.animate().alpha(1f).setDuration(200)

                    btnEvent.visibility = View.VISIBLE
                    btnEvent.animate().alpha(1f).setDuration(200)

                    btnInfo.visibility = View.VISIBLE
                    btnInfo.animate().alpha(1f).setDuration(200)
                }
            }
        })
    }
        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment FilterScreen.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                EventListScreen().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }