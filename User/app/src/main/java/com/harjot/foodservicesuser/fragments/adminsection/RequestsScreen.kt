package com.harjot.foodservicesuser.fragments.adminsection

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harjot.foodservicesuser.authentication.AuthenticationActivity
import com.harjot.foodservicesuser.databinding.FragmentRequestsScreenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RequestsScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestsScreen : Fragment() {
    val binding by lazy {
        FragmentRequestsScreenBinding.inflate(layoutInflater)
    }
    val adminActivity by lazy {
        activity as AdminActivity
    }
    val auth = Firebase.auth
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            var intent = Intent(adminActivity, AuthenticationActivity::class.java)
            startActivity(intent)
            adminActivity.finish()
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
         * @return A new instance of fragment RequestsScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RequestsScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}