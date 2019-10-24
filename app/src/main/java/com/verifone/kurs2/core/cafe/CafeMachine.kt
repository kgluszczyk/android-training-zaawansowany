package com.verifone.kurs2.core.cafe

import com.verifone.kurs2.core.di.MilkFat
import timber.log.Timber
import javax.inject.Inject

class CafeMachine @Inject constructor(val coffe: Coffe,  @MilkFat val milks: Set<@JvmSuppressWildcards Milk>) {
    init {
        Timber.d("DI ${this}")
    }
}