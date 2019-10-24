package com.verifone.kurs2.core.di

import com.verifone.kurs2.core.cafe.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module
abstract class MilkAnimalModule {

    @Binds
    @IntoSet
    abstract fun bindsWholeMilk(milk: WholeMilk) : Milk

    @Binds
    @IntoSet
    abstract fun bindsLightMilk(milk: LightMilk) : Milk

    @Binds
    @IntoSet
    abstract fun bindsSkimMilk(milk: SkimMilk) : Milk

    @Binds
    @IntoSet
    abstract fun bindsHalfnHalfMilk(milk: HalfnHalfMilk) : Milk
}