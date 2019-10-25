package com.verifone.kurs2.showcaseContentProviders.domain

import androidx.lifecycle.LiveData
import com.verifone.kurs2.core.di.FragmentScope
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import javax.inject.Inject

@FragmentScope
class ObserveCoffeeIntake @Inject constructor(
    val coffeeIntakeDao: CoffeeIntakeDao
) {

    fun execute(): LiveData<List<CoffeeIntake>> =
        coffeeIntakeDao.observeAll()
}