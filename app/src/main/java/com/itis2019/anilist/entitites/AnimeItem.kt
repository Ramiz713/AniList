package com.itis2019.anilist.entitites

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "anime_data")
data class AnimeItem(
    @SerializedName("end_date")
    val endDate: String?,
    val episodes: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @PrimaryKey
    @SerializedName("mal_id")
    val id: Int,
    val members: Int,
    val rank: Int,
    val score: Double,
    @SerializedName("start_date")
    val startDate: String,
    val title: String,
    val type: String,
    val url: String
) : Parcelable
