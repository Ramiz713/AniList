package com.itis2019.anilist.entitites.manga

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.itis2019.anilist.entitites.anime.Summary
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Related(
    val Adaptation: List<Adaptation>?,
    @SerializedName("Side story")
    val SideStory: List<SideStory>?,
    val Summary: List<Summary>?
) : Parcelable
