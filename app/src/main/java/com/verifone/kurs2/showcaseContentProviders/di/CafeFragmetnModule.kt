package com.verifone.kurs2.showcaseContentProviders.di

import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.presentation.CafeViewModel
import dagger.Module
import dagger.Provides

@Module
class CafeFragmetnModule {
    @Provides
    @FragmentScope
    fun provideConfeIntakeDao(db: AppDatabase) = db.coffeeIntakeDao()

    @Provides
    @FragmentScope
    fun provideCafeViewModelFactory(
        observeCoffeeIntake: ObserveCoffeeIntake,
        saveCoffeeIntake: SaveCoffeeIntake,
        getMood: GetMood
    ) = CafeViewModel.Factory(observeCoffeeIntake, saveCoffeeIntake, getMood)
}