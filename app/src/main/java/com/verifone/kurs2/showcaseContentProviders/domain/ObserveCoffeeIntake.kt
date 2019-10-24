package com.verifone.kurs2.showcaseContentProviders.domain

import com.verifone.kurs2.App
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class ObserveCoffeeIntake(
    val coffeeIntakeDao: CoffeeIntakeDao = (App.container[AppDatabase::class.java] as AppDatabase).coffeeIntakeDao()
) {

    fun execute(): Flowable<List<CoffeeIntake>> =
        coffeeIntakeDao.observeAll()
            .subscribeOn(Schedulers.io())

}