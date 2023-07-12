package com.sample.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelatedTopic(
    val FirstURL: String,
    val Icon: Icon,
    val Result: String,
    val Text: String
): Parcelable