package com.verifone.kurs2.showcaseContentProviders.di

import com.verifone.kurs2.core.di.FragmentScope
import com.verifone.kurs2.core.repository.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class ContentProvidersFragmentModule{
    @Provides
    @FragmentScope
    fun providesDao(appDatabase: AppDatabase) = appDatabase.coffeeIntakeDao()
}