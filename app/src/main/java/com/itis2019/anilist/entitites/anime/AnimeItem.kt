package com.itis2019.anilist.entitites.anime

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.itis2019.anilist.db.converters.*
import com.itis2019.anilist.entitites.manga.Genre
import com.itis2019.anilist.entitites.manga.Related
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
    val startDate: String?,
    val title: String,
    val type: String,
    val url: String,
    @Embedded(prefix = "aired_")
    val aired: Aired?,
    val airing: Boolean,
    val background: String?,
    val broadcast: String?,
    val duration: String?,
    val ending_themes: List<String>?,
    val favorites: Int,
    @TypeConverters(GenresConverter::class)
    val genres: List<Genre>?,
    @TypeConverters(LicensorsConverter::class)
    val licensors: List<Licensor>?,
    val opening_themes: List<String>?,
    val popularity: Int,
    val premiered: String?,
    @TypeConverters(ProducersConverter::class)
    val producers: List<Producer>?,
    val rating: String?,
    @TypeConverters(Related::class)
    val related: Related?,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String?,
    val scored_by: Int,
    val source: String?,
    val status: String?,
    @TypeConverters(StudioConverter::class)
    val studios: List<Studio>?,
    val synopsis: String?,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>?,
    val trailer_url: String?
) : Parcelable

@Parcelize
data class Studio(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class Licensor(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class Producer(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class Aired(
    val from: String?,
    @TypeConverters(PropConverter::class)
    val prop: Prop?,
    val string: String?,
    val to: String?
) : Parcelable

@Parcelize
data class Prop(
    val from: From,
    val to: To
) : Parcelable

@Parcelize
data class To(
    val day: Int,
    val month: Int,
    val year: Int
) : Parcelable

@Parcelize
data class From(
    val day: Int,
    val month: Int,
    val year: Int
) : Parcelable

@Parcelize
data class Summary(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
) : Parcelable
