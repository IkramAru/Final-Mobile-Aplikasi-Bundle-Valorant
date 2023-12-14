package com.d121211104.bundle.data.response

import com.d121211104.bundle.data.models.Bundle
import com.d121211104.bundle.data.models.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBundleResponse(
    @SerialName("data")
    val data: List<Bundle>,
    @SerialName("status")
    val status: Int?,
    @SerialName("bundle")
    val bundle: List<Data>
)