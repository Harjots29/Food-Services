package com.harjot.foodservicesuser.fragments.infosection

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.authentication.AuthenticationActivity
import com.harjot.foodservicesuser.databinding.FragmentInfoScreenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoScreen : Fragment() {
    val binding by lazy {
        FragmentInfoScreenBinding.inflate(layoutInflater)
    }
    val auth = Firebase.auth
    lateinit var mainScreenBottomNav: MainScreenBottomNav

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
                Log.d("ImageURI", "Selected Image URI: $it") // Debugging Log

                binding.ivProfileImage.setImageURI(uri) // Try setting image directly

                Glide.with(requireContext()) // Glide for better loading
                    .load(uri)
                    .placeholder(R.drawable.ic_person) // Placeholder image
                    .error(R.drawable.ic_person) // Error fallback
                    .into(binding.ivProfileImage)
        }
    }

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivChangeImage.setOnClickListener {
            pickImage.launch("image/*")
        }
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            var intent = Intent(mainScreenBottomNav, AuthenticationActivity::class.java)
            startActivity(intent)
            mainScreenBottomNav.finish()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}