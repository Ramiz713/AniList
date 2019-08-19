package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.anime.Prop

class PropConverter {

    @TypeConverter
    fun serialize(prop: Prop?): String =
        Gson().toJson(prop, object : TypeToken<Prop>() {}.type)

    @TypeConverter
    fun deserialize(jsonProp: String?): Prop? =
        Gson().fromJson(jsonProp, object : TypeToken<Prop>() {}.type)
}
