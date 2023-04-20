package com.example.demologin.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.example.demologin.response.LoginResponse
import com.example.demologin.databinding.ActivityBaseBinding
import com.example.demologin.utils.PreferenceManager
import com.example.demologin.utils.Utils
import es.dmoral.toasty.Toasty

open class BaseActivity : AppCompatActivity() {
     val binding: ActivityBaseBinding by lazy { ActivityBaseBinding.inflate(layoutInflater) }
    open var activity: Activity = this
    lateinit var utils: Utils
    var deviceId = ""
    lateinit var pref: PreferenceManager
    lateinit var loginResponse: LoginResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        utils = Utils(this)
        deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        pref = PreferenceManager(this)
    }
    fun successToast(msg: String) {
//Add library in settings.gradle -  maven{url 'https://jitpack.io'}
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun errorToast(msg: String) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show()
    }

}