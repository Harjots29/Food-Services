package com.harjot.foodservicesuser.fragments.homesection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.databinding.FragmentAboutFoodItemScreenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFoodItemScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFoodItemScreen : Fragment() {
    val binding by lazy {
        FragmentAboutFoodItemScreenBinding.inflate(layoutInflater)
    }
    lateinit var mainScreenBottomNav: MainScreenBottomNav
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScreenBottomNav = activity as MainScreenBottomNav

        mainScreenBottomNav.binding.btnQuantity.visibility = View.VISIBLE
        mainScreenBottomNav.binding.btnAddToCart.visibility = View.VISIBLE
        mainScreenBottomNav.binding.btnHome.visibility = View.GONE
        mainScreenBottomNav.binding.btnEvent.visibility = View.GONE
        mainScreenBottomNav.binding.btnInfo.visibility = View.GONE
        mainScreenBottomNav.binding.btnCart.visibility = View.GONE

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
        val price = binding.tvPrice.text.toString()
        mainScreenBottomNav.binding.ivAdd.setOnClickListener {
            mainScreenBottomNav.binding.tvQuantity.text =
                (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt() + 1).toString()
            binding.tvPrice.text = (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt()
                    * price.toInt()).toString()
        }
        mainScreenBottomNav.binding.ivSubtract.setOnClickListener {
            if(mainScreenBottomNav.binding.tvQuantity.text.toString().toInt() > 1) {
                mainScreenBottomNav.binding.tvQuantity.text =
                    (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt() - 1).toString()
                binding.tvPrice.text = (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt()
                        * price.toInt()).toString()
            }
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
         * @return A new instance of fragment AboutFoodItemScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFoodItemScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}