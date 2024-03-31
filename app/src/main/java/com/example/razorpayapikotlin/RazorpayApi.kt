package com.example.razorpayapikotlin

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface RazorpayApi {
    @GET("customers")
    fun getCustomer(@HeaderMap header: HashMap<String, String>): Observable<UserRazorpayListModel>
    @POST("customers")
    fun postCustomer(@HeaderMap header: HashMap<String, String>, @Body post: PostDataModel): Observable<UserRazorpayListModel>



    companion object Factory {
        fun creatretrofit(): RazorpayApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .baseUrl("https://api.razorpay.com/v1/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(RazorpayApi::class.java)
            return retrofit
        }
    }
}