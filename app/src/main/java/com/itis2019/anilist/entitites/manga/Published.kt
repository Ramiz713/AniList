package com.itis2019.anilist.entitites.manga

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Published(
    val from: String,
    @Expose(serialize = false)
    val prop: String,
    @SerializedName("string")
    val allDateInString: String,
    val to: String
) : Parcelable
