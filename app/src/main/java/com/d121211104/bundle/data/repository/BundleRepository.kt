package com.d121211104.bundle.data.repository

import com.d121211104.bundle.data.response.GetBundleResponse
import com.d121211104.bundle.data.source.remote.ApiService

class BundleRepository(private val apiService: ApiService) {

    suspend fun getBundle(): GetBundleResponse {
        return apiService.getBundle()
    }

}