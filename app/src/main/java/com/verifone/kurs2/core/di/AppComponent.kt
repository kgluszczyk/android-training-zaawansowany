package com.verifone.kurs2.core.di

import com.verifone.kurs2.App
import com.verifone.kurs2.MainActivity
import com.verifone.kurs2.showcaseContentProviders.ContentProvidersFragment
import com.verifone.kurs2.showcaseContentProviders.di.ContentProvidersFragmentComponent
import com.verifone.kurs2.showcaseContentProviders.di.ContentProvidersFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [MilkAnimalModule::class, MilkPlantModule::class, MilkTypeModule::class, AppModule::class])
interface AppComponent {
    fun inject(app:App)
    fun inject(activity: MainActivity)
   // fun inject(fragment: ContentProvidersFragment)

    fun plusContentProvidersFragmentComponent(contentProvidersFragmentModule: ContentProvidersFragmentModule) : ContentProvidersFragmentComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun isVegan(@Named("isVegan")  boolean: Boolean): Builder

        fun build(): AppComponent
    }
}