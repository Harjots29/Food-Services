package com.harjot.foodservicesuser.fragments.homesection

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.adapters.HomeAddsAdapter
import com.harjot.foodservicesuser.adapters.WalkthroughAdapter
import com.harjot.foodservicesuser.databinding.FragmentHomeScreenBinding
import com.harjot.foodservicesuser.models.HomeAddsModel
import com.harjot.foodservicesuser.models.WalkthroughModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreen : Fragment() {
    val binding by lazy {
        FragmentHomeScreenBinding.inflate(layoutInflater)
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
        val viewPager = binding.viewPager
        val adapter = HomeAddsAdapter(getScreens())
        viewPager.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAutoScroll(binding.viewPager, 3000L)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun getScreens(): List<HomeAddsModel> {
        return listOf(
            HomeAddsModel(R.drawable.add_1),
            HomeAddsModel(R.drawable.add_2),
            HomeAddsModel(R.drawable.add_3),
            HomeAddsModel(R.drawable.add_4),
        )
    }
    fun setupAutoScroll(viewPager: ViewPager2, interval: Long) {
        val handler = Handler(Looper.getMainLooper())
        var currentPage = 0

        val runnable = object : Runnable {
            override fun run() {
                val adapter = viewPager.adapter ?: return
                currentPage = (currentPage + 1) % adapter.itemCount // Loop back to the first item
                viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, interval)
            }
        }
        // Start the automatic scrolling
        handler.postDelayed(runnable, interval)

        // Optional: Stop scrolling when the user interacts with the ViewPager2
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }
}