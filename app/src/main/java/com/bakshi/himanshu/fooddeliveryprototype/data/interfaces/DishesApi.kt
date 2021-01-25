package com.bakshi.himanshu.fooddeliveryprototype.data.interfaces

import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish
import retrofit2.Response
import retrofit2.http.GET

interface DishesApi {
    @GET("b/GVYX")
    suspend fun getDishes(): Response<List<Dish>>
}