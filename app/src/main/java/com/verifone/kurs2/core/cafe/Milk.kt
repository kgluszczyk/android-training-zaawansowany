package com.verifone.kurs2.core.cafe

import timber.log.Timber
import javax.inject.Inject

open class Milk constructor(val type: MilkType) {
    init {
        Timber.d("DI ${this}")
    }
}

class WholeMilk @Inject constructor() : Milk(MilkType.WHOLE)
class LightMilk @Inject constructor() : Milk(MilkType.LIGHT)
class SkimMilk @Inject constructor() : Milk(MilkType.SKIM)
class HalfnHalfMilk @Inject constructor() : Milk(MilkType.HALFnHALF)
class RiceMilk @Inject constructor() : Milk(MilkType.RICE)
class SoyaMilk @Inject constructor() : Milk(MilkType.SOYA)

enum class MilkType(val isVegan: Boolean) {
    LIGHT(false), SKIM(false), HALFnHALF(false), WHOLE(false), RICE(true), SOYA(true)
}