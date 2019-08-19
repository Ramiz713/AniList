package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.anime.Licensor

class LicensorsConverter {

    @TypeConverter
    fun serialize(listOfLicensors: List<Licensor>?): String =
        Gson().toJson(listOfLicensors, object : TypeToken<List<Licensor>>() {}.type)

    @TypeConverter
    fun deserialize(jsonOfLicensors: String?): List<Licensor>? =
        Gson().fromJson(jsonOfLicensors, object : TypeToken<List<Licensor>>() {}.type)
}
