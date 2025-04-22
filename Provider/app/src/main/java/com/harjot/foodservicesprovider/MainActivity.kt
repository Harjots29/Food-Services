package com.harjot.foodservicesprovider

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.harjot.foodservicesprovider.authentication.AuthenticationActivity
import com.harjot.foodservicesprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        Handler(Looper.getMainLooper())
            .postDelayed({
                if(auth.currentUser != null) {
                    var intent = Intent(this, MainScreenBottomNav::class.java)
                    startActivity(intent)
                    this.finish()
                }else {
                    var intent = Intent(this, AuthenticationActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }
            }, 1500)
    }
}