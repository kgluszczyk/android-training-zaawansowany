package com.verifone.kurs2.core.di

import com.verifone.kurs2.core.cafe.Milk
import com.verifone.kurs2.core.cafe.MilkType
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module
class MilkTypeModule {

    @Provides
    @IntoSet
    @MilkFat
    fun provideWHOLEMilk() = Milk(MilkType.WHOLE)

    @Provides
    @IntoSet
    @MilkFat
    fun provideHALFnHALFMilk() = Milk(MilkType.HALFnHALF)


    @Provides
    @IntoSet
    @MilkFat
    fun provideSKIMMilk() = Milk(MilkType.SKIM)


    @Provides
    @IntoSet
    @MilkFat
    fun provideRICEMilk() = Milk(MilkType.RICE)

    @Provides
    @IntoSet
    @MilkFat
    fun provideSOYAMilk() = Milk(MilkType.SOYA)

    @Provides
    @IntoSet
    @MilkFat
    fun provideLIGHTMilk() = Milk(MilkType.LIGHT)


    @Provides
    fun provideLimitedOffer(@Named("isVegan") isVegan: Boolean, @MilkFat offers:Set<@JvmSuppressWildcards Milk>) = offers.filter { it.type.isVegan xor isVegan}
}