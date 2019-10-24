package com.verifone.kurs2.core.cafe

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cafe @Inject constructor(val coffeMachine: CafeMachine, val coffeMachine2: CafeMachine, val milks: Set<@JvmSuppressWildcards Milk>){
    init {
        Timber.d( "DI ${this.milks.toString()}")
    }
}