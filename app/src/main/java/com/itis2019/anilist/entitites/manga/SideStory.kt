package com.itis2019.anilist.entitites.manga

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SideStory(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
) : Parcelable
