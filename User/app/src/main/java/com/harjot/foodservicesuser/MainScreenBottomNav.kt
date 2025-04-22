package com.harjot.foodservicesuser

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.harjot.foodservicesuser.databinding.ActivityMainScreenBottomNavBinding
import com.harjot.foodservicesuser.fragments.homesection.HomeScreen

class MainScreenBottomNav : AppCompatActivity() {
    val binding by lazy {
        ActivityMainScreenBottomNavBinding.inflate(layoutInflater)
    }
    lateinit var navController: NavController
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        navController = findNavController(R.id.navHostFragment)
        binding.btnCart.visibility = View.VISIBLE
        binding.btnQuantity.visibility = View.GONE
        binding.btnAddToCart.visibility = View.GONE

//        val blurEffect = RenderEffect.createBlurEffect(
//            10f,
//            10f,
//            Shader.TileMode.MIRROR
//        )
//        binding.linearLayout.setRenderEffect(blurEffect)



        binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
        binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))

        binding.btnHome.setOnClickListener {
            binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
            binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))
            binding.btnEvent.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivEvent.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

            binding.btnCart.visibility = View.VISIBLE
            binding.fabEventAdd.visibility = View.GONE
            binding.btnQuantity.visibility = View.GONE
            binding.btnAddToCart.visibility = View.GONE

            navController.navigate(R.id.homeScreen)
        }
        binding.btnEvent.setOnClickListener {
            binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnEvent.setCardBackgroundColor(getResources().getColor(R.color.red))
            binding.ivEvent.setColorFilter(ContextCompat.getColor(this, R.color.white))
            binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

            binding.btnCart.visibility = View.GONE
            binding.fabEventAdd.visibility = View.VISIBLE
            binding.btnQuantity.visibility = View.GONE
            binding.btnAddToCart.visibility = View.GONE

            navController.navigate(R.id.eventListScreen)
        }
        binding.btnInfo.setOnClickListener {
            binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnEvent.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivEvent.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.red))
            binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.white))

            binding.fabEventAdd.visibility = View.GONE
            binding.btnCart.visibility = View.GONE
            binding.btnQuantity.visibility = View.GONE
            binding.btnAddToCart.visibility = View.GONE

            navController.navigate(R.id.infoScreen)
        }
        binding.btnCart.setOnClickListener {
            navController.navigate(R.id.cartScreen)
            binding.btnCart.visibility = View.GONE
            binding.btnQuantity.visibility = View.GONE
            binding.btnAddToCart.visibility = View.GONE
            binding.fabEventAdd.visibility = View.GONE
            binding.btnHome.visibility = View.GONE
            binding.btnInfo.visibility = View.GONE
            binding.btnEvent.visibility = View.GONE

        }
    }

    override fun onBackPressed() {

        val navController = findNavController(R.id.navHostFragment)
        val currentFragment = navController.currentDestination?.id

        when (currentFragment) {
            R.id.homeScreen -> {
                finish()
            }
            R.id.eventListScreen -> {
                binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
                binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))
                binding.btnEvent.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivEvent.setColorFilter(ContextCompat.getColor(this, R.color.red))
                binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

                binding.btnCart.visibility = View.VISIBLE
                binding.fabEventAdd.visibility = View.GONE
                binding.btnQuantity.visibility = View.GONE
                binding.btnAddToCart.visibility = View.GONE
                navController.popBackStack(R.id.homeScreen,false)
            }
            R.id.infoScreen -> {
                binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
                binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))
                binding.btnEvent.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivEvent.setColorFilter(ContextCompat.getColor(this, R.color.red))
                binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

                binding.btnCart.visibility = View.VISIBLE
                binding.fabEventAdd.visibility = View.GONE
                binding.btnQuantity.visibility = View.GONE
                binding.btnAddToCart.visibility = View.GONE
                navController.popBackStack(R.id.homeScreen,false)
            }
            R.id.addEventsFragment -> {
                binding.fabEventAdd.visibility = View.VISIBLE
                super.onBackPressed()
            }
            R.id.aboutFoodItemScreen -> {
                binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
                binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))
                binding.btnEvent.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivEvent.setColorFilter(ContextCompat.getColor(this, R.color.red))
                binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

                binding.btnCart.visibility = View.VISIBLE
                binding.btnHome.visibility = View.VISIBLE
                binding.btnInfo.visibility = View.VISIBLE
                binding.btnEvent.visibility = View.VISIBLE
                binding.fabEventAdd.visibility = View.GONE
                binding.btnQuantity.visibility = View.GONE
                binding.btnAddToCart.visibility = View.GONE

                binding.tvQuantity.text = "1"

                navController.popBackStack(R.id.homeScreen,false)
            }
            R.id.cartScreen -> {
                binding.btnCart.visibility = View.VISIBLE
                binding.btnQuantity.visibility = View.GONE
                binding.btnAddToCart.visibility = View.GONE
                binding.fabEventAdd.visibility = View.GONE
                binding.btnHome.visibility = View.VISIBLE
                binding.btnInfo.visibility = View.VISIBLE
                binding.btnEvent.visibility = View.VISIBLE

                navController.popBackStack(R.id.homeScreen,false)
            }
            else -> {
                // For other fragments, use the default back action
                super.onBackPressed()
            }
        }
    }

}