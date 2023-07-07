package com.sample.model.network.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorViewModel(
    var errorMessage: String
) : Parcelable