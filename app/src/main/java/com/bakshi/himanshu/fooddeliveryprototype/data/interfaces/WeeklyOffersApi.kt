package com.bakshi.himanshu.fooddeliveryprototype.data.interfaces

import com.bakshi.himanshu.fooddeliveryprototype.data.models.WeeklyOffer
import retrofit2.Response
import retrofit2.http.GET

interface WeeklyOffersApi {
    @GET("b/XKY9")
    suspend fun getOffers(): Response<List<WeeklyOffer>>
}