package com.example.demologin.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.demologin.adapter.ItemAdapter
import com.example.demologin.apiCalling.RetrofitClient
import com.example.demologin.databinding.ActivityAddItemBinding
import com.example.demologin.response.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddItemActivity : BaseActivity() {
    val b: ActivityAddItemBinding by lazy { ActivityAddItemBinding.inflate(layoutInflater) }
    lateinit var itemAdapter: ItemAdapter
    override var activity: Activity = this
    var itemList: ArrayList<ApiResponse.Payload> = ArrayList()
    var id = ""

    companion object {
        const val USER_ID = "USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        initView()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initView() {

        itemAdapter = ItemAdapter(activity, itemList)
        b.rvItem.adapter = itemAdapter
        id = intent.getStringExtra(USER_ID).toString()
        addItemApi()
    }


    private fun addItemApi() {
        if (utils.isNetworkAvailable()) {
            val apiService: Call<ApiResponse?> = RetrofitClient.getClient.getItemCategoryList(id)

//            val apiService = getClient(APIService::class.java)
//            val call = apiService.getItemCategoryList("RES1617874827ZTC105101")
            apiService.enqueue(object : Callback<ApiResponse?> {

                override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                    // Handle successful response here
                    utils.dismissProgress()
                    if (response.isSuccessful) {
                        val apiResponse: ApiResponse? = response.body()!!
                        when (apiResponse?.status) {
                            200 -> {
                                b.rvItem.visibility = View.VISIBLE
                                itemAdapter = ItemAdapter(activity, itemList)
                                b.rvItem.adapter = itemAdapter
                                itemList.clear()
                                itemList.addAll(apiResponse.payload)
                                itemAdapter.notifyItemChanged()
                            }
                        }



                    }
                }
                override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                }
            })
        }
    }
}