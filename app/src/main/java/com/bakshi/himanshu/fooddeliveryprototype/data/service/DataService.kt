package com.bakshi.himanshu.fooddeliveryprototype.data.service

import com.bakshi.himanshu.fooddeliveryprototype.data.interfaces.DataApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataService {

    val baseUrl = "https://jsonkeeper.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): DataApi = retrofit.create(DataApi::class.java)
}