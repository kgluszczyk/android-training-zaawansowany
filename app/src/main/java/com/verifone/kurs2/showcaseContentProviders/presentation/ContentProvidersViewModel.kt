package com.verifone.kurs2.showcaseContentProviders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ContentProvidersViewModel : ViewModel() {


    private lateinit var observeCoffeeIntake: ObserveCoffeeIntake
    private lateinit var  saveCoffeeIntake: SaveCoffeeIntake
    private lateinit var  getMood: GetMood

    private val data: LiveData<List<CoffeeIntake>> by lazy {
        observeCoffeeIntake.execute()
    }

    fun data():LiveData<List<CoffeeIntake>> = data

    val disposables = CompositeDisposable()

    fun saveCoffeeIntake(intake: Float) {
        Single.fromCallable {
            val newCoffeeIntake = CoffeeIntake(amount = intake, mood = getMood.execute().mood)
            Timber.d("Zapisuje - czekam")
            try {
                Thread.sleep(5000)
            }catch (e: InterruptedException){
                Timber.e(e)
            }
            Timber.d("Zapisuje - juz nie")
            saveCoffeeIntake.execute(newCoffeeIntake)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                //data.value = listOf()
            })
            .also {
                disposables.add(it)
            }
    }

    fun init(
        observeCoffeeIntake: ObserveCoffeeIntake,
        saveCoffeeIntake: SaveCoffeeIntake,
        getMood: GetMood
    ) {
        this.observeCoffeeIntake = observeCoffeeIntake
        this.saveCoffeeIntake = saveCoffeeIntake
        this.getMood = getMood

    }

    fun observeCoffeeIntake() =
        observeCoffeeIntake.execute()

    override fun onCleared() {
        super.onCleared()
        Timber.d("Zapisuje - juz czyszcze")
        disposables.clear()

    }
}