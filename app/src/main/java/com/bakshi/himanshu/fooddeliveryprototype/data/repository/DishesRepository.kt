package com.bakshi.himanshu.fooddeliveryprototype.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.data.interfaces.DataApi
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish
import com.bakshi.himanshu.fooddeliveryprototype.utils.Constants
import com.bakshi.himanshu.fooddeliveryprototype.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*

class DishesRepository(private val dataApi: DataApi) {

    val TAG = DishesRepository::class.java.simpleName

    private val dishesLiveData = MutableLiveData<List<Dish>>()
    private var getDishesJob: Job? = null

    // Returns a LiveData object with a list of dishes
    fun getDishes(context: Context, useMocks: Boolean = false): LiveData<List<Dish>> {

        if (useMocks) {
            getMockDishes(context)
        } else {
            getFromNetwork()
        }

        return dishesLiveData
    }

    // Get dishes from network
    private fun getFromNetwork() {

        getDishesJob = CoroutineScope(Dispatchers.IO).launch {

            val deferredResponse = async { dataApi.getDishes() }
            val response = deferredResponse.await()

            if (response.isSuccessful) {
                response.body()?.let {
                    onDishesDownloaded(it)
                    Log.d(TAG, "getFromNetwork, dishes downloaded, value: $it)}")
                }
            } else {
                onError(java.lang.Exception(response.message()))
            }
        }
    }

    // Get dishes from local mocks
    private fun getMockDishes(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            val localDishesJson =
                JsonUtils.getRawResource(context, R.raw.mock_dishes)
            if (localDishesJson != null) {
                val dishes = getDishesFromJson(localDishesJson)
                if (dishes == null) {
                    onError(Exception(Constants.DISHES_NOT_FOUND))
                } else {
                    onDishesDownloaded(dishes)
                }
            } else {
                onError(Exception(Constants.RESOURCE_NOT_FOUND))
            }
            Log.d(TAG, "getMockDishes - local dishes json: $localDishesJson")
        }
    }

    private fun getDishesFromJson(dishesJson: String): List<Dish>? {

        var dishes: List<Dish>? = null
        try {
            val typeToken = object : TypeToken<List<Dish>>() {}.type
            dishes = Gson().fromJson(dishesJson, typeToken)
        } catch (e: JsonParseException) {
            Log.e(TAG, "getDishesFromJson - Enable to parse mock dishes json: ${e.message}")
            onError(e)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "getDishesFromJson - Unable to parse mock dishes json: ${e.message}")
            onError(e)
        }

        return dishes
    }


    private fun onDishesDownloaded(dishes: List<Dish>) {
        dishesLiveData.postValue(dishes)
    }

    private fun onError(e: Exception) {
        Log.e(TAG, "onError - Unable to get dishes, exception: ${e.message}")
    }

    fun clear() {
        getDishesJob?.cancel()
    }
}