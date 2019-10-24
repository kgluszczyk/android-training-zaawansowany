package com.verifone.kurs2.core.cafe

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class Coffe @Inject constructor(){
    init {
        Timber.d( "DI ${this}")
    }
}