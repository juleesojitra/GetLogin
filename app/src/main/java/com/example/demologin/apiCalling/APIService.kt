package com.example.demologin.apiCalling
import com.example.demologin.response.LoginResponse
import com.example.demologin.request.LoginRequest
import com.example.demologin.response.ApiResponse
import retrofit2.Call
import retrofit2.http.*


interface APIService {

    //Auth
    @POST("api/auth/sign-in")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse?>

    @GET("/api/item/get-item-category-list")
    fun getItemCategoryList(@Query("restaurant_id") restaurantId: String): Call<ApiResponse?>
}