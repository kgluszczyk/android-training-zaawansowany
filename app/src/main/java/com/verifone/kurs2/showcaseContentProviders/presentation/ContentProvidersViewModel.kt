package com.verifone.kurs2.showcaseContentProviders.presentation

import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentProvidersViewModel(
    val observeCoffeeIntake: ObserveCoffeeIntake,
    val saveCoffeeIntake: SaveCoffeeIntake,
    val getMood: GetMood
) {

    fun saveCoffeeIntake(intake: Float): Single<CoffeeIntake> {
        return Single.fromCallable {
            val newCoffeeIntake = CoffeeIntake(
                amount = intake,
                mood = getMood.execute().mood
            )

            saveCoffeeIntake.execute(newCoffeeIntake)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun observeCoffeeIntake() =
        observeCoffeeIntake.execute()
            .observeOn(AndroidSchedulers.mainThread())
}