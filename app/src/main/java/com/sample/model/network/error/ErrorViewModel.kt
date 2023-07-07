package com.sample.model.network.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorViewMode(
    var errorCode: String,
    var errorMessageResourceId: Int
) : Parcelable