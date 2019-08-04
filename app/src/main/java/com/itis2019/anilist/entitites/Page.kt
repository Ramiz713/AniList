package com.itis2019.anilist.entitites

import com.google.gson.annotations.SerializedName

data class Page<T>(
    @SerializedName("request_cache_expiry")
    val cacheExpiry: Int,
    @SerializedName("request_cached")
    val isCached: Boolean,
    @SerializedName("request_hash")
    val hash: String,
    val top: List<T>
)
