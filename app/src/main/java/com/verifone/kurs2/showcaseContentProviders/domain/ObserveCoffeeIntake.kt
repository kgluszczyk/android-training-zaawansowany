package com.verifone.kurs2.showcaseContentProviders.domain

import com.verifone.kurs2.App
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ObserveCoffeeIntake @Inject constructor(
    private val coffeeIntakeDao: CoffeeIntakeDao
) {

    fun execute(): Flowable<List<CoffeeIntake>> =
        coffeeIntakeDao.observeAll()
            .subscribeOn(Schedulers.io())

}