package com.verifone.kurs2.showcaseContentProviders.di

import com.verifone.kurs2.core.di.FragmentScope
import com.verifone.kurs2.showcaseContentProviders.ContentProvidersFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ContentProvidersFragmentModule::class])
interface ContentProvidersFragmentComponent{
    fun inject(contentProvidersFragment: ContentProvidersFragment)
}