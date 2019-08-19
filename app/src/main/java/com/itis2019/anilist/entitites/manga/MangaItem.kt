package com.itis2019.anilist.entitites.manga

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.itis2019.anilist.db.converters.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "manga_data")
data class MangaItem(
    @PrimaryKey
    @SerializedName("mal_id")
    val id: Int,
    val background: String? = "",
    val chapters: Int = 0,
    val favorites: Int = 0,
    @TypeConverters(GenresConverter::class)
    val genres: List<Genre>? = listOf(),
    @SerializedName("image_url")
    val imageUrl: String,
    val members: Int = 0,
    val popularity: Int = 0,
    val publishing: Boolean = false,
    val rank: Int,
    @TypeConverters(RelatedConverter::class)
    val related: Related? = null,
    val request_cache_expiry: Int = 0,
    val request_cached: Boolean = false,
    val request_hash: String? = "",
    val score: Double,
    val scored_by: Int = 0,
    @TypeConverters(SerializationConverter::class)
    val serializations: List<Serialization>? = listOf(),
    val status: String? = "",
    val synopsis: String? = "",
    val title: String,
    val title_english: String? = "",
    val title_japanese: String? = "",
    @TypeConverters(SynonymsConverter::class)
    val title_synonyms: List<String>? = listOf(),
    val type: String,
    val volumes: Int = 0,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @Embedded(prefix = "published_")
    val published: Published? = Published(
        startDate
            ?: "", "", "", endDate ?: ""
    ),
    @TypeConverters(AuthorsConverter::class)
    var authors: List<Author>? = listOf(),
    val url: String
) : Parcelable
