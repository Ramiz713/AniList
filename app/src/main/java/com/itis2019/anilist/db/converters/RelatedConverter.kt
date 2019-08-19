package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.manga.Related

class RelatedConverter {

    @TypeConverter
    fun serialize(related: Related?): String =
        Gson().toJson(related, object : TypeToken<Related>() {}.type)

    @TypeConverter
    fun deserialize(jsonRelated: String?): Related? =
        Gson().fromJson(jsonRelated, object : TypeToken<Related>() {}.type)
}
