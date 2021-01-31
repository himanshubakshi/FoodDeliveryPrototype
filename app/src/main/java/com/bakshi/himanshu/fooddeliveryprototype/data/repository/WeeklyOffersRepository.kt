package com.bakshi.himanshu.fooddeliveryprototype.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.data.interfaces.DataApi
import com.bakshi.himanshu.fooddeliveryprototype.data.models.WeeklyOffer
import com.bakshi.himanshu.fooddeliveryprototype.utils.Constants
import com.bakshi.himanshu.fooddeliveryprototype.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*

class WeeklyOffersRepository(private val dataApi: DataApi) {

    val TAG = WeeklyOffersRepository::class.java.simpleName

    private val weeklyOffersLiveData = MutableLiveData<List<WeeklyOffer>>()
    private var getWeeklyOffersJob: Job? = null

    // Returns a LiveData object with a list of weekly offers
    fun getWeeklyOffers(context: Context, useMocks: Boolean = false): LiveData<List<WeeklyOffer>> {

        if (useMocks) {
            getMockOffers(context)
        } else {
            getFromNetwork()
        }

        return weeklyOffersLiveData
    }

    // Get offers from network
    private fun getFromNetwork() {

        getWeeklyOffersJob = CoroutineScope(Dispatchers.IO).launch {

            val deferredResponse = async { dataApi.getOffers() }
            val response = deferredResponse.await()

            if (response.isSuccessful) {
                response.body()?.let {
                    onOffersDownloaded(it)
                    Log.d(TAG, "getFromNetwork, weekly offers downloaded, value: $it)}")
                }
            } else {
                onError(java.lang.Exception(response.message()))
            }
        }
    }

    // Get offers from local mocks
    private fun getMockOffers(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            val localOffersJson =
                JsonUtils.getRawResource(context, R.raw.mock_weekly_offers)
            if (localOffersJson != null) {
                val offers = getOffersFromJson(localOffersJson)
                if (offers == null) {
                    onError(Exception(Constants.OFFERS_NOT_FOUND))
                } else {
                    onOffersDownloaded(offers)
                }
            } else {
                onError(Exception(Constants.RESOURCE_NOT_FOUND))
            }
            Log.d(TAG, "getMockOffers - local offers json: $localOffersJson")
        }
    }

    private fun getOffersFromJson(offersJson: String): List<WeeklyOffer>? {

        var offers: List<WeeklyOffer>? = null
        try {
            val typeToken = object : TypeToken<List<WeeklyOffer>>() {}.type
            offers = Gson().fromJson(offersJson, typeToken)
        } catch (e: JsonParseException) {
            Log.e(TAG, "getOffersFromJson - Enable to parse mock weekly offers json: ${e.message}")
            onError(e)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "getOffersFromJson - Unable to parse mock weekly offers json: ${e.message}")
            onError(e)
        }

        return offers
    }

    private fun onOffersDownloaded(offers: List<WeeklyOffer>) {
        weeklyOffersLiveData.postValue(offers)
    }

    private fun onError(e: Exception) {
        Log.e(TAG, "onError - Unable to get weekly offers, exception: ${e.message}")
    }

    fun clear() {
        getWeeklyOffersJob?.cancel()
    }
}