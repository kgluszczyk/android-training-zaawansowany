package com.verifone.kurs2.showcaseContentProviders.di

import com.verifone.kurs2.showcaseContentProviders.CafeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CafeFragmetnModule::class])
interface CafeFragmentComponent{
    fun inject(fragment: CafeFragment)
}