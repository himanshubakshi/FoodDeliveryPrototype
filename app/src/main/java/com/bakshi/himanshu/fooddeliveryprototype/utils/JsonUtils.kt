package com.bakshi.himanshu.fooddeliveryprototype.utils

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object JsonUtils {
    val TAG = JsonUtils::class.java.simpleName

    // Load a local raw file
    suspend fun getRawResource(context: Context, resourceId: Int): String? {
        var resultResource: String? = null

        if (resourceId != 0) {
            CoroutineScope(Dispatchers.IO).launch {
                val deferredResource = async {
                    try {
                        context.resources.openRawResource(resourceId).bufferedReader().use {
                            it.readText()
                        }
                    } catch (e: NotFoundException) {
                        Log.e(TAG, "getRawResource: Resource not found, exception: ${e.message}")
                        null
                    }
                }

                resultResource = deferredResource.await()
            }.join()
        }

        return resultResource
    }
}