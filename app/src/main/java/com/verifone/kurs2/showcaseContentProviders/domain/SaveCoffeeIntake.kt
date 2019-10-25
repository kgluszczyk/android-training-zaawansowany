package com.verifone.kurs2.showcaseContentProviders.domain

import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import java.lang.IllegalStateException
import javax.inject.Inject

class SaveCoffeeIntake @Inject constructor(
    val coffeeIntakeDao: CoffeeIntakeDao
) {

    fun execute(coffeeIntake: CoffeeIntake): CoffeeIntake {
        val id = coffeeIntakeDao.insert(coffeeIntake)
        if (id < 0) {
            throw IllegalStateException("$coffeeIntake not saved")
        }

        return coffeeIntake.copy(
            id = id
        )
    }
}