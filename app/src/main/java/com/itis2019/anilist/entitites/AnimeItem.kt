package com.itis2019.anilist.entitites

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "anime_data")
data class AnimeItem(
    val end_date: String?,
    val episodes: Int,
    val image_url: String,
    @PrimaryKey
    val mal_id: Int,
    val members: Int,
    val rank: Int,
    val score: Double,
    val start_date: String,
    val title: String,
    val type: String,
    val url: String
) : Parcelable
