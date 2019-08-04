package com.itis2019.anilist.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.Related

class RelatedConverter {

    @TypeConverter
    fun serialize(related: Related?): String =
        Gson().toJson(related, object : TypeToken<Related>() {}.type)

    @TypeConverter
    fun deserialize(related: String?): Related? =
        Gson().fromJson(related, object : TypeToken<Related>() {}.type)
}
