package com.d121211104.bundle.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Data(
//    val assetPath: String,
    val description: String,
    val displayIcon: String,
//    val displayIcon2: String,
    val displayName: String,
//    val displayNameSubText: String,
//    val extraDescription: String,
//    val promoDescription: String,
//    val useAdditionalContext: Boolean,
//    val uuid: String,
//    val verticalPromoImage: String
): Parcelable