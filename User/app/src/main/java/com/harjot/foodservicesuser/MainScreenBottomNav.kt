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
            binding.btnFilter.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivFilter.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

            navController.navigate(R.id.homeScreen)
        }
        binding.btnFilter.setOnClickListener {
            binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnFilter.setCardBackgroundColor(getResources().getColor(R.color.red))
            binding.ivFilter.setColorFilter(ContextCompat.getColor(this, R.color.white))
            binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))

            navController.navigate(R.id.filterScreen)
        }
        binding.btnInfo.setOnClickListener {
            binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnFilter.setCardBackgroundColor(getResources().getColor(R.color.white))
            binding.ivFilter.setColorFilter(ContextCompat.getColor(this, R.color.red))
            binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.red))
            binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.white))

            navController.navigate(R.id.infoScreen)
        }
    }

    override fun onBackPressed() {

        val navController = findNavController(R.id.navHostFragment)
        val currentFragment = navController.currentDestination?.id

        when (currentFragment) {
            R.id.homeScreen -> {
                finish()
            }
            R.id.filterScreen -> {
                binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
                binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))
                binding.btnFilter.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivFilter.setColorFilter(ContextCompat.getColor(this, R.color.red))
                binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))
                navController.popBackStack(R.id.homeScreen,false)
            }
            R.id.infoScreen -> {
                binding.btnHome.setCardBackgroundColor(getResources().getColor(R.color.red))
                binding.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white))
                binding.btnFilter.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivFilter.setColorFilter(ContextCompat.getColor(this, R.color.red))
                binding.btnInfo.setCardBackgroundColor(getResources().getColor(R.color.white))
                binding.ivInfo.setColorFilter(ContextCompat.getColor(this, R.color.red))
                navController.popBackStack(R.id.homeScreen,false)
            }
            else -> {
                // For other fragments, use the default back action
                super.onBackPressed()
            }
        }
    }

}