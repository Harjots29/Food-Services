package com.harjot.foodservicesprovider.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.harjot.foodservicesprovider.MainScreenBottomNav
import com.harjot.foodservicesprovider.R
import com.harjot.foodservicesprovider.databinding.FragmentLoginScreenBinding
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginScreen : Fragment() {
    val binding by lazy {
        FragmentLoginScreenBinding.inflate(layoutInflater)
    }
    val auth = FirebaseAuth.getInstance()
    lateinit var authenticationActivity: AuthenticationActivity
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authenticationActivity = activity as AuthenticationActivity
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignup.setOnClickListener {
            authenticationActivity.navController.navigate(R.id.signupScreen)
        }
        binding.tvForgotPassword.setOnClickListener {
            authenticationActivity.navController.navigate(R.id.forgotPasswordScreen)
        }
        binding.btnSubmit.setOnClickListener {
            if (binding.etUsername.text.toString().trim().isNullOrEmpty()){
                binding.etUsername.error = "Enter Email"
                binding.etUsername.requestFocus()
            }else if(Pattern.matches(Patterns.EMAIL_ADDRESS.toString(),binding.etUsername.text.toString().trim())==false){
                binding.etUsername.error = "Enter Valid Email"
                binding.etUsername.requestFocus()
            } else if (binding.etPassword.text.toString().trim().isNullOrEmpty())
            {
                binding.etPassword.error = "Enter Password"
                binding.etPassword.requestFocus()
            } else if(binding.etPassword.length()<6){
                binding.etPassword.error="Enter Atleast 6 Characters"
            }else{
                binding.btnSubmit.isClickable = false
                var email = binding.etUsername.text.toString().trim()
                var password = binding.etPassword.text.toString().trim()
                auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        binding.btnSubmit.isClickable = true
                        var intent = Intent(authenticationActivity,MainScreenBottomNav::class.java)
                        startActivity(intent)
                        authenticationActivity.finish()
                    }
                    .addOnFailureListener {
                        binding.btnSubmit.isClickable = true
                        Toast.makeText(authenticationActivity,
                            "Sorry! Your Email or Password is incorrect",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
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
         * @return A new instance of fragment LoginScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}