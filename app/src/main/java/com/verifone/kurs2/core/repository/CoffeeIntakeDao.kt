package com.verifone.kurs2.core.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.verifone.kurs2.core.entity.CoffeeIntake
import io.reactivex.Flowable

// Można użyć jako @Query(selectAll), ale wtedy tracimy autocomplete
const val selectAll = "SELECT * FROM caffeine"

@Dao
interface CoffeeIntakeDao {

    @Insert //ta funkcja mogłaby obsługiwać też listę CoffeeIntake
    fun insert(coffeeIntake: CoffeeIntake): Long

    @Update
    fun update(coffeeIntake: CoffeeIntake): Int

    @Delete
    fun delete(coffeeIntake: CoffeeIntake): Int

    @Query("SELECT * FROM caffeine")
    fun getAll(): List<CoffeeIntake>

    @Query("SELECT * FROM caffeine")
    fun observeAll(): Flowable<List<CoffeeIntake>>

}