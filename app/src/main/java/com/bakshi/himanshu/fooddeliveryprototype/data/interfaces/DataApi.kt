package com.bakshi.himanshu.fooddeliveryprototype.data.interfaces

import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish
import com.bakshi.himanshu.fooddeliveryprototype.data.models.WeeklyOffer
import retrofit2.Response
import retrofit2.http.GET

interface DataApi {
    @GET("b/74WX")
    suspend fun getDishes(): Response<List<Dish>>

    @GET("b/Y7QC")
    suspend fun getOffers(): Response<List<WeeklyOffer>>
}