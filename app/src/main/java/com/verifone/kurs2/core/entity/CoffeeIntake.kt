package com.verifone.kurs2.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "caffeine")
data class CoffeeIntake(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "mood") val mood: Mood
)

class MoodTypeConverter {

    @TypeConverter
    fun fromModel(value: Mood) = Gson().toJson(value)

    @TypeConverter
    fun fromString(value: String) = Gson().fromJson(value, Mood::class.java)
}