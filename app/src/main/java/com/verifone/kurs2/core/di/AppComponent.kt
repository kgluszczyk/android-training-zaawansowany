package com.verifone.kurs2.core.di

import com.verifone.kurs2.App
import com.verifone.kurs2.MainActivity
import com.verifone.kurs2.showcaseContentProviders.CafeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Component(modules = [MilkAnimalModule::class, MilkPlantModule::class, MilkTypeModule::class])
@Singleton
interface AppComponent {
    fun inject(app:App)
    fun inject(activity: MainActivity)
    fun inject(fragment: CafeFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun isVegan(@Named("isVegan")  boolean: Boolean): Builder

        fun build(): AppComponent
    }
}