package com.itis2019.anilist.entitites

data class Page<T>(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val top: List<T>
)
