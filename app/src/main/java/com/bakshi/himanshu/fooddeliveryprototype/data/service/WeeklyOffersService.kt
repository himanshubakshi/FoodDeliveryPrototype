package com.bakshi.himanshu.fooddeliveryprototype.data.service

import com.bakshi.himanshu.fooddeliveryprototype.data.interfaces.WeeklyOffersApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeeklyOffersService {

    val baseUrl = "https://jsonkeeper.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): WeeklyOffersApi = retrofit.create(WeeklyOffersApi::class.java)
}