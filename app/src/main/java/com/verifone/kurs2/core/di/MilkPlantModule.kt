package com.verifone.kurs2.core.di

import com.verifone.kurs2.core.cafe.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module
abstract class MilkPlantModule {

    @Binds
    @IntoSet
    abstract fun bindsRiceMilk(milk: RiceMilk) : Milk

    @Binds
    @IntoSet
    abstract fun bindsSoyaMilk(milk: SoyaMilk) : Milk

}