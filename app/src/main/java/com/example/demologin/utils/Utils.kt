package com.example.demologin.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.demologin.R

class Utils(val context: Context) {
    var customProgress: CustomProgress? = null


    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


    fun showProgress(activity: Activity) {
        try {
            if (customProgress != null && customProgress!!.isShowing) customProgress!!.dismiss()
            if (customProgress == null) customProgress =
                CustomProgress(
                    context
                )
            customProgress!!.setCancelable(false)
            customProgress!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissProgress() {
        if (customProgress != null && customProgress!!.isShowing) customProgress?.dismiss()
        customProgress == null
    }

    fun showToast(msg: String) {
        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)
        val textView: TextView = view.findViewById(R.id.custom_toast_text)
        textView.text = msg
        toast.view = view
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 70)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }


}