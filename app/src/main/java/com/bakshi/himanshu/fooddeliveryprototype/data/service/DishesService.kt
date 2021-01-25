package com.bakshi.himanshu.fooddeliveryprototype.data.service

import com.bakshi.himanshu.fooddeliveryprototype.data.interfaces.DishesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DishesService {

    val baseUrl = "https://jsonkeeper.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): DishesApi = retrofit.create(DishesApi::class.java)
}