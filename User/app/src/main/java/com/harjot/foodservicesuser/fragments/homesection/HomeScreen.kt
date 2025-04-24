package com.harjot.foodservicesuser.fragments.homesection

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.adapters.EventListAdapter
import com.harjot.foodservicesuser.adapters.HomeAddsAdapter
import com.harjot.foodservicesuser.adapters.HomeTrendingAdapter
import com.harjot.foodservicesuser.adapters.WalkthroughAdapter
import com.harjot.foodservicesuser.databinding.FragmentHomeScreenBinding
import com.harjot.foodservicesuser.interfaces.HomeTrendingInterface
import com.harjot.foodservicesuser.models.EventsModel
import com.harjot.foodservicesuser.models.HomeAddsModel
import com.harjot.foodservicesuser.models.HomeTrendingModel
import com.harjot.foodservicesuser.models.WalkthroughModel
import java.io.IOException
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreen : Fragment(),HomeTrendingInterface {
    val binding by lazy {
        FragmentHomeScreenBinding.inflate(layoutInflater)
    }
    val location_permissions_req_code=1000
    val mainScreenBottomNav: MainScreenBottomNav by lazy { activity as MainScreenBottomNav }
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var  trendingList = ArrayList<HomeTrendingModel>()
    lateinit var homeTrendingAdapter: HomeTrendingAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(mainScreenBottomNav)
        if (checkPermissions()){
            print("Permissions checked")
            getLastLocation()
        }
        else{
            requestPermission()
        }
        homeTrendingAdapter = HomeTrendingAdapter(trendingList,mainScreenBottomNav,this)
        binding.rvHomeTrendings.layoutManager = LinearLayoutManager(mainScreenBottomNav,
            LinearLayoutManager.HORIZONTAL,
            false)
        binding.rvHomeTrendings.adapter = homeTrendingAdapter

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
        val viewPager = binding.viewPager
        val adapter = HomeAddsAdapter(getScreens())
        viewPager.adapter = adapter

        trendingList.clear()
        trendingList.add(
            HomeTrendingModel(
                name = "Burger",
                imageRes = R.drawable.add_1,
                catersName = "Harjot Singh"
            ))
        trendingList.add(
            HomeTrendingModel(
                name = "Pizza",
                imageRes = R.drawable.img_1,
                catersName = "Harjot Singh"
            ))
        trendingList.add(
            HomeTrendingModel(
                name = "Noodles",
                imageRes = R.drawable.img_noodles,
                catersName = "Harjot Singh"
            ))
        trendingList.add(
            HomeTrendingModel(
                name = "Sandwich",
                imageRes = R.drawable.img_sandwich,
                catersName = "Harjot Singh"
            ))
        trendingList.add(
            HomeTrendingModel(
                name = "Manchurian",
                imageRes = R.drawable.img_manchurian,
                catersName = "Harjot Singh"
            ))
        homeTrendingAdapter.notifyDataSetChanged()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAutoScroll(binding.viewPager, 3000L)

//        var previousScrollY = 0
//
//        binding.homeScrollView.viewTreeObserver.addOnScrollChangedListener {
//            val scrollY = binding.homeScrollView.scrollY
//            val btnHome = mainScreenBottomNav.binding.btnHome
//            val btnEvent = mainScreenBottomNav.binding.btnEvent
//            val btnInfo = mainScreenBottomNav.binding.btnInfo
//            val btnCart = mainScreenBottomNav.binding.btnCart
//
//            if (scrollY > previousScrollY) {
//                // Scrolling Down - Hide
//                btnHome.animate().alpha(0f).setDuration(200).withEndAction {
//                    btnHome.visibility = View.GONE
//                }
//                btnEvent.animate().alpha(0f).setDuration(200).withEndAction {
//                    btnEvent.visibility = View.GONE
//                }
//                btnInfo.animate().alpha(0f).setDuration(200).withEndAction {
//                    btnInfo.visibility = View.GONE
//                }
//                btnCart.animate().alpha(0f).setDuration(200).withEndAction {
//                    btnCart.visibility = View.GONE
//                }
//            } else if (scrollY < previousScrollY) {
//                // Scrolling Up - Show
//                btnHome.visibility = View.VISIBLE
//                btnHome.animate().alpha(1f).setDuration(200)
//
//                btnEvent.visibility = View.VISIBLE
//                btnEvent.animate().alpha(1f).setDuration(200)
//
//                btnInfo.visibility = View.VISIBLE
//                btnInfo.animate().alpha(1f).setDuration(200)
//
//                btnCart.visibility = View.VISIBLE
//                btnCart.animate().alpha(1f).setDuration(200)
//            }
//            previousScrollY = scrollY
//        }

    }
    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            mainScreenBottomNav,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            mainScreenBottomNav,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION , Manifest.permission.ACCESS_FINE_LOCATION),
            1000
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            location_permissions_req_code ->{
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getLastLocation()
                }
                else{
                    Toast.makeText(mainScreenBottomNav, "dekhle bhai esa krega?", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val userLati = location.latitude
                    val userLongi = location.longitude
                    val address = getCompleteAddressString(userLati, userLongi)
                    binding.addressTv.text = address
                } else {
                    // Fallback to actively request location updates
                    val locationRequest = LocationRequest.create().apply {
                        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        interval = 5000 // 5 seconds interval for location updates like enabling gps
                        fastestInterval = 2000
                        numUpdates = 1 // Get only one update
                    }
                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                val location = locationResult.lastLocation
                                val address = getCompleteAddressString(
                                    location.latitude,
                                    location.longitude
                                )
                                binding.addressTv.text = address
                            }
                        },
                        Looper.getMainLooper()
                    )
                }
            }
            .addOnFailureListener { e ->
                binding.addressTv.text = "Failed to get location: ${e.message}"
            }
    }


    private fun getCompleteAddressString(lat: Double, long: Double): String {
        val geocoder = Geocoder(mainScreenBottomNav, Locale.getDefault())
        return try {
            val addressList = geocoder.getFromLocation(lat, long, 1)
            if ( !addressList.isNullOrEmpty( )) {
                addressList[0].getAddressLine(0) ?: "Address not found"
            } else {
                "No address found"
            }
        } catch (e: IOException) {
            "Error: ${e.message}"
        } catch (e: Exception) {
            "Unexpected error occurred"
        }
    }

    private fun getScreens(): List<HomeAddsModel> {
        return listOf(
            HomeAddsModel(R.drawable.add_1),
            HomeAddsModel(R.drawable.add_2),
            HomeAddsModel(R.drawable.add_3),
            HomeAddsModel(R.drawable.add_4),
        )
    }
    fun setupAutoScroll(viewPager: ViewPager2, interval: Long) {
        val handler = Handler(Looper.getMainLooper())
        var currentPage = 0

        val runnable = object : Runnable {
            override fun run() {
                val adapter = viewPager.adapter ?: return
                currentPage = (currentPage + 1) % adapter.itemCount // Loop back to the first item
                viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, interval)
            }
        }
        // Start the automatic scrolling
        handler.postDelayed(runnable, interval)

        // Optional: Stop scrolling when the user interacts with the ViewPager2
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }

    override fun onItemClick(position: Int) {
        when(position){
            0->{
                findNavController().navigate(R.id.aboutBurgerScreen)
            }
            1->{

            }
            2->{
                findNavController().navigate(R.id.aboutNoodlesScreen)
            }
            3->{
                findNavController().navigate(R.id.aboutSandwichScreen)
            }
            4->{
                findNavController().navigate(R.id.aboutManchurianScreen)
            }
        }

    }
}