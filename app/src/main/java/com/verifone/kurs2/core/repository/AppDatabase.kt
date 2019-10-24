package com.verifone.kurs2.core.repository

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.entity.MoodTypeConverter
import timber.log.Timber

lateinit var DATABASE: AppDatabase

fun Context.getDatabase(): AppDatabase {
    if (::DATABASE.isInitialized) {
        return DATABASE
    }

    DATABASE = Room.databaseBuilder(this, AppDatabase::class.java, "app-db")
        .addMigrations(migration_1_2)
        .build()

    return DATABASE
}


@Database(
    entities = [CoffeeIntake::class],
    version = 2
)
@TypeConverters(MoodTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coffeeIntakeDao() : CoffeeIntakeDao

}

private fun Migration.log(){
    Timber.d("Migration from $startVersion to $endVersion started!")
}

//todo check what's wrong!?
val migration_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        log()
        database.execSQL("DROP TABLE `caffeine`")
        database.execSQL("CREATE TABLE IF NOT EXISTS `caffeine`  (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `amount` LONG, 'mood' TEXT)")
    }

}