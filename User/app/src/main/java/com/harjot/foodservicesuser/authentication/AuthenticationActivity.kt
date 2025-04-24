package com.harjot.foodservicesuser.authentication

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.harjot.foodservicesuser.R

class AuthenticationActivity : AppCompatActivity() {
    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authentication)
        navController = findNavController(R.id.navController)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun snackbar(view: View, message: String,){
        val snackbar = Snackbar.make(view, "$message", Snackbar.LENGTH_INDEFINITE)
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