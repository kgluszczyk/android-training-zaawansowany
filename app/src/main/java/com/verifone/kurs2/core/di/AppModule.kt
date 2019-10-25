package com.verifone.kurs2.core.di

import android.content.Context
import com.verifone.kurs2.App
import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.core.repository.getDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{

    @Provides
    @Singleton
    fun providesContext(app:App) = app.applicationContext

    @Provides
    @Singleton
    fun provideDb(context:Context) : AppDatabase{
        return context.getDatabase()
    }
}