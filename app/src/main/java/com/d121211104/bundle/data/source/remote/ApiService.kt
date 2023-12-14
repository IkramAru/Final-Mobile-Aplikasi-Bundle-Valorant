package com.d121211104.bundle.data.source.remote

import com.d121211104.bundle.data.response.GetBundleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("v1/bundles")
    suspend fun getBundle(
        @Query("status") status: Int = 200,
    ): GetBundleResponse
}