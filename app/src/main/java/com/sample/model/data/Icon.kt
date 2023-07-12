package com.sample.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Icon(
    val Height: String,
    val URL: String,
    val Width: String
): Parcelable