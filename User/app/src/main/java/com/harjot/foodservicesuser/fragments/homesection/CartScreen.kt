package com.harjot.foodservicesuser.fragments.homesection

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.adapters.CartListAdapter
import com.harjot.foodservicesuser.databinding.FragmentCartScreenBinding
import com.harjot.foodservicesuser.models.OrdersModel
import kotlin.text.equals

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartScreen : Fragment() {
    val binding by lazy {
        FragmentCartScreenBinding.inflate(layoutInflater)
    }
    val mainScreenBottomNav by lazy { activity as MainScreenBottomNav }
    var arrayList = ArrayList<OrdersModel>()
    lateinit var adapter: CartListAdapter

    var database = Firebase.firestore
    val collectionName = "Orders"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = CartListAdapter(arrayList)
        binding.rvCartList.layoutManager = LinearLayoutManager(mainScreenBottomNav)
        binding.rvCartList.adapter = adapter

        arrayList.clear()
        database.collection(collectionName)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                for (snapshot in snapshots!!.documentChanges) {
                    val userModel = convertObject(snapshot.document)

                    when (snapshot.type) {
                        DocumentChange.Type.ADDED -> {
                            userModel?.let { arrayList.add(it) }
                            Log.e(ContentValues.TAG, "userModelList ${arrayList.size}")
                        }
                        DocumentChange.Type.MODIFIED -> {
                            userModel?.let {
                                var index = getIndex(userModel)
                                if (index > -1) {
                                    arrayList.set(index, it)
                                }
                            }
                        }
                        DocumentChange.Type.REMOVED -> {
                            userModel?.let {
                                var index = getIndex(userModel)
                                if (index > -1) {
                                    arrayList.removeAt(index)
                                }
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

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

        binding.tvBack.setOnClickListener {
            mainScreenBottomNav.onBackPressed()
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
         * @return A new instance of fragment CartScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun convertObject(snapshot: QueryDocumentSnapshot) : OrdersModel?{
        val orderModel: OrdersModel =
            snapshot.toObject(OrdersModel::class.java)
        orderModel.id = snapshot.id ?: ""
        return orderModel
    }
    fun getIndex(userModel: OrdersModel) : Int{
        var index = -1
        index = arrayList.indexOfFirst { element ->
            element.id?.equals(userModel.id) == true
        }
        return index
    }
}