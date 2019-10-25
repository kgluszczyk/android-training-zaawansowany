package com.verifone.kurs2.showcaseContentProviders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CafeViewModel(
    val observeCoffeeIntake: ObserveCoffeeIntake,
    val saveCoffeeIntake: SaveCoffeeIntake,
    val getMood: GetMood

) : ViewModel() {

    val disposables = CompositeDisposable()

    class Factory(
        private val observeCoffeeIntake: ObserveCoffeeIntake,
        private val saveCoffeeIntake: SaveCoffeeIntake,
        private val getMood: GetMood
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CafeViewModel(observeCoffeeIntake, saveCoffeeIntake, getMood) as T
        }

    }

    fun saveCoffeeIntake(intake: Float) {
        val dispose = Single.fromCallable {
            Thread.sleep(5000)
            val newCoffeeIntake = CoffeeIntake(
                amount = intake,
                mood = getMood.execute().mood
            )

            saveCoffeeIntake.execute(newCoffeeIntake)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
        disposables.add(dispose)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun observeCoffeeIntake() = observeCoffeeIntake.execute()
}