package com.d121211104.bundle.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Bundle(
    val `data`: List<Data>,
    val status: Int
): Parcelable