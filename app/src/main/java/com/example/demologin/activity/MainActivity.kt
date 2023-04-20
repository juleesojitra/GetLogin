package com.example.demologin.activity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.demologin.adapter.ItemAdapter
import com.example.demologin.apiCalling.RetrofitClient
import com.example.demologin.databinding.ActivityMainBinding
import com.example.demologin.response.ApiResponse
import com.example.demologin.response.LoginResponse
import com.example.demologin.utils.Const
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    val b: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var itemAdapter: ItemAdapter

    var itemList: ArrayList<ApiResponse.Payload> = ArrayList()
    var id = ""

    companion object{
      const val  USER_ID = "USER_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        initView()
    }

    private fun initView() {

        itemAdapter = ItemAdapter(activity, itemList)
        b.rvItem.adapter = itemAdapter
        id = intent.getStringExtra(USER_ID).toString()
        addItemApi()
    }

    private fun addItemApi() {
        if (utils.isNetworkAvailable()) {
            utils.showProgress(activity)
            val item = RetrofitClient.getClient.getItemCategoryList(id)
            item.enqueue(object : Callback<ApiResponse?> {

                override fun onResponse(
                    call: Call<ApiResponse?>,
                    response: Response<ApiResponse?>
                ) {
                    utils.dismissProgress()
                    if (response.isSuccessful) {
                        val apiResponse: ApiResponse = response.body()!!
                        when (apiResponse.status) {
                            200 -> {
                                b.rvItem.visibility = View.VISIBLE
                                itemList.clear()
                                itemList.addAll(apiResponse.payload)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                    utils.dismissProgress()
                }
            })
        }
    }


}




