package com.verifone.kurs2.core.di

import androidx.room.Room
import com.verifone.kurs2.App
import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.core.repository.migration_1_2
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.presentation.CafeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDb(app: App) =
        Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "app-db")
            .addMigrations(migration_1_2)
            .build()
}