package com.example.demologin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.demologin.response.LoginResponse
import com.example.demologin.apiCalling.RetrofitClient
import com.example.demologin.databinding.ActivityLoginBinding
import com.example.demologin.request.LoginRequest
import com.example.demologin.utils.Const
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    val b: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        initView()
        clickEvent()
    }

    private fun initView() {

    }

    private fun clickEvent() {
        b.btnLogin.setOnClickListener {
            if (isValid()) {
                loginApi()
            }
        }
    }

    private fun loginApi() {
        if (utils.isNetworkAvailable()) {
            utils.showProgress(this)
            val loginRequest = LoginRequest(
                b.etEmail.text.toString().trim(),
                b.etPassword.text.toString().trim(),
                "restaurant"
            )
            val login: Call<LoginResponse?> = RetrofitClient.getClient.login(loginRequest)
            login.enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(
                    call: Call<LoginResponse?>,
                    response: Response<LoginResponse?>
                ) {
                    utils.dismissProgress()
                    if (response.isSuccessful) {
                        val loginResponse = response.body()!!
                        when (loginResponse.status) {
                            "200" -> {
                                pref.setString(Const.userData, Gson().toJson(response.body()))
                                pref.setString(Const.token, loginResponse.payload.token)
                                pref.setString(Const.id, loginResponse.payload.restaurant_id)
//                                successToast(loginResponse.error as String)
//                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                successToast("Login Successfully")

                                checkProfileStatus()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    utils.dismissProgress()
                    utils.showToast(t.message.toString())
                }

            })
        }
    }

    fun isValid(): Boolean {
        if (b.etEmail.text.toString().isEmpty()) {
            errorToast("Enter Email Address")
        } else if (b.etPassword.text.toString().isEmpty()) {
            errorToast("Enter PassWord")
        }
        return true
    }

    fun checkProfileStatus() {
        if (pref.getString(Const.userData)?.isNotEmpty()!!) {
            loginResponse =
                Gson().fromJson(pref.getString(Const.userData), LoginResponse::class.java)
        }
        finish()
        startActivity(Intent(activity, MainActivity::class.java)
            .putExtra(MainActivity.USER_ID, loginResponse.payload.restaurant_id))
        Log.d("TAG", "onResponse: ${loginResponse.payload.restaurant_id}")

    }
}