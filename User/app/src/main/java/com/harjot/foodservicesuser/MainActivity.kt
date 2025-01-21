package com.harjot.foodservicesuser

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harjot.foodservicesuser.adapters.WalkthroughAdapter
import com.harjot.foodservicesuser.authentication.AuthenticationActivity
import com.harjot.foodservicesuser.databinding.ActivityMainBinding
import com.harjot.foodservicesuser.models.WalkthroughModel

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(binding.root)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = WalkthroughAdapter(getScreens())
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.prevButton.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
                binding.nextButton.visibility = if (position == adapter.itemCount - 1) View.GONE else View.VISIBLE
                binding.getStartedButton.visibility = if (position == adapter.itemCount - 1) View.VISIBLE else View.GONE
            }
        })
        binding.prevButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }

        binding.nextButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }

        binding.getStartedButton.setOnClickListener {
            if(auth.currentUser!=null){
                var intent = Intent(this,MainScreenBottomNav::class.java)
                startActivity(intent)
                this.finish()
            }else{
                var intent = Intent(this,AuthenticationActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }
    }

    private fun getScreens(): List<WalkthroughModel> {
        return listOf(
            WalkthroughModel(R.drawable.img_1, "Welcome!", "Super excited you’re with us!"),
            WalkthroughModel(R.drawable.img_2, "Discover!", "Get ready for some amazing deals and updates."),
            WalkthroughModel(R.drawable.img_3, "Get Started!", "Get ready for a great experience.")
        )
    }
}