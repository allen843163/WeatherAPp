package com.allen.core.remote.epa.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PM25 (
    val DataCreationDate: String,
    val ItemUnit: String,
    val PM25: String,
    val Site: String,
    val county: String
): Parcelable