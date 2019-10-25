package com.verifone.kurs2.showcaseContentProviders.presentation

import androidx.lifecycle.ViewModel
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CafeViewModel(

) : ViewModel() {

    val disposables = CompositeDisposable()
    lateinit var observeCoffeeIntake: ObserveCoffeeIntake
    lateinit var saveCoffeeIntake: SaveCoffeeIntake
    lateinit var getMood: GetMood

    fun init(observeCoffeeIntake: ObserveCoffeeIntake,
             saveCoffeeIntake: SaveCoffeeIntake,
             getMood: GetMood){
        this.observeCoffeeIntake = observeCoffeeIntake
        this.saveCoffeeIntake = saveCoffeeIntake
        this.getMood = getMood
    }

    fun saveCoffeeIntake(intake: Float){
        val dispose =  Single.fromCallable {
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