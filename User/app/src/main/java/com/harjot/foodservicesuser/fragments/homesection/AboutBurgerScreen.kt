package com.harjot.foodservicesuser.fragments.homesection

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.databinding.FragmentAboutBurgerScreenBinding
import com.harjot.foodservicesuser.models.OrdersModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutBurgerScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutBurgerScreen : Fragment() {
    val binding by lazy {
        FragmentAboutBurgerScreenBinding.inflate(layoutInflater)
    }
    lateinit var mainScreenBottomNav: MainScreenBottomNav
    var database = Firebase.firestore
    val collectionName = "Orders"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScreenBottomNav = activity as MainScreenBottomNav

       mainScreenBottomNav.aboutItemScreenViewVisibilities()

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
        var itemType = "Simple Burger"
        var price = binding.tvPrice.text.toString()
        var tvPrice = binding.tvPrice.text
        mainScreenBottomNav.binding.ivAdd.setOnClickListener {
            mainScreenBottomNav.binding.tvQuantity.text =
                (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt() + 1).toString()
            binding.tvPrice.text = (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt()
                    * price.toInt()).toString()
            tvPrice = binding.tvPrice.text
        }
        mainScreenBottomNav.binding.ivSubtract.setOnClickListener {
            if(mainScreenBottomNav.binding.tvQuantity.text.toString().toInt() > 1) {
                mainScreenBottomNav.binding.tvQuantity.text =
                    (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt() - 1).toString()
                binding.tvPrice.text = (mainScreenBottomNav.binding.tvQuantity.text.toString().toInt()
                        * price.toInt()).toString()
                tvPrice = binding.tvPrice.text
            }
        }

        binding.tvBack.setOnClickListener {
            mainScreenBottomNav.onBackPressed()
        }

        binding.cvSimpleBurger.setCardBackgroundColor(resources.getColor(R.color.red))
        binding.tvSimpleBurger.setTextColor(resources.getColor(R.color.white))
        binding.cvVegBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
        binding.tvVegBurger.setTextColor(resources.getColor(R.color.grey))
        binding.cvCrispyBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
        binding.tvCrispyBurger.setTextColor(resources.getColor(R.color.grey))

        binding.cvSimpleBurger.setOnClickListener {
            binding.cvSimpleBurger.setCardBackgroundColor(resources.getColor(R.color.red))
            binding.tvSimpleBurger.setTextColor(resources.getColor(R.color.white))
            binding.cvVegBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
            binding.tvVegBurger.setTextColor(resources.getColor(R.color.grey))
            binding.cvCrispyBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
            binding.tvCrispyBurger.setTextColor(resources.getColor(R.color.grey))

            itemType = "Simple Burger"
            binding.tvPrice.text = "25"
            price = binding.tvPrice.text.toString()
            tvPrice = binding.tvPrice.text
        }
        binding.cvVegBurger.setOnClickListener {
            binding.cvSimpleBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
            binding.tvSimpleBurger.setTextColor(resources.getColor(R.color.grey))
            binding.cvVegBurger.setCardBackgroundColor(resources.getColor(R.color.red))
            binding.tvVegBurger.setTextColor(resources.getColor(R.color.white))
            binding.cvCrispyBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
            binding.tvCrispyBurger.setTextColor(resources.getColor(R.color.grey))

            itemType = "Veg Burger"
            binding.tvPrice.text = "30"
            price = binding.tvPrice.text.toString()
            tvPrice = binding.tvPrice.text
        }
        binding.cvCrispyBurger.setOnClickListener {
            binding.cvSimpleBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
            binding.tvSimpleBurger.setTextColor(resources.getColor(R.color.grey))
            binding.cvVegBurger.setCardBackgroundColor(resources.getColor(R.color.light_grey))
            binding.tvVegBurger.setTextColor(resources.getColor(R.color.grey))
            binding.cvCrispyBurger.setCardBackgroundColor(resources.getColor(R.color.red))
            binding.tvCrispyBurger.setTextColor(resources.getColor(R.color.white))

            itemType = "Crispy Burger"
            binding.tvPrice.text = "40"
            price = binding.tvPrice.text.toString()
            tvPrice = binding.tvPrice.text
        }

        mainScreenBottomNav.binding.btnAddToCart.setOnClickListener { view->
            binding.tvPrice.text = price
            var model = OrdersModel(
                id = "",
                item = binding.tvItemName.text.toString(),
                price = tvPrice.toString(),
                quantity = mainScreenBottomNav.binding.tvQuantity.text.toString(),
//                location = mainScreenBottomNav.binding.tvLocation.text.toString(),
                itemType = itemType
            )
            database.collection(collectionName).add(model)
                .addOnSuccessListener {
                    mainScreenBottomNav.binding.btnAddToCart.visibility = View.GONE
                    mainScreenBottomNav.binding.btnQuantity.visibility = View.GONE
                    mainScreenBottomNav.binding.notificationDot.visibility = View.VISIBLE
                    mainScreenBottomNav.binding.btnCart.visibility = View.VISIBLE

                    mainScreenBottomNav.snackbar(view,"Your item has been added to cart")
                }.addOnFailureListener { exception->
                    mainScreenBottomNav.binding.btnAddToCart.visibility = View.VISIBLE
                    mainScreenBottomNav.binding.btnQuantity.visibility = View.VISIBLE
                    mainScreenBottomNav.binding.btnCart.visibility = View.GONE
                    mainScreenBottomNav.binding.notificationDot.visibility = View.GONE

                    mainScreenBottomNav.snackbar(view,"Something went wrong : ${exception.message}")
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
            AboutBurgerScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}