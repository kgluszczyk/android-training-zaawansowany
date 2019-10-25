package com.verifone.kurs2

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.verifone.kurs2.core.cafe.Cafe
import com.verifone.kurs2.core.cafe.Milk
import com.verifone.kurs2.core.di.AppComponent
import com.verifone.kurs2.core.di.DaggerAppComponent
import com.verifone.kurs2.core.di.MilkPlantModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject
import kotlin.random.Random

const val ACTION_IN_APP_MESSAGE = "com.verifone.kurs2.IN_APP_ACTION"

class App : Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var cafe: Cafe
    @Inject
    lateinit var listOfMilks: List<@JvmSuppressWildcards Milk>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // kiedy używamy dużo content providerów,
        // to musimy pamiętać o tym, że ich
        // onCreate może zostać wywołane przed
        // Application::onCreate

        // w tym przypadku to jest miejsce na inicjalizację
        // np. timbera
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        Timber.d("App on create")
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .isVegan(Random.nextBoolean())
            .build()
        appComponent.inject(this)
        Timber.d("List of offers: $listOfMilks")
        val receiver = InAppBroadcastReceiver()
        registerReceiver(
            receiver,
            IntentFilter(ACTION_IN_APP_MESSAGE)
        )

        testRxJava()
    }

    private class InAppBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val source = intent?.getStringExtra("source")
            Timber.d("In app message received: $source")
        }
    }

    fun testRxJava() {
        Timber.d("Testing rx Java".appendThreadName())
        Observable.fromCallable(::createTestData)

        // Observable.fromCallable przyjmuje funkcję, która zostanie później uruchomiona
        Observable.fromCallable<List<Int>> {
            Timber.d("Create stream on thread".appendThreadName())
            listOf(1, 2, 3, 4)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { Timber.d("$it current data on thread".appendThreadName()) }
            .observeOn(Schedulers.io())
            .subscribe({
                           Timber.d("New element from stream: $it".appendThreadName())
                       }, {
                           Timber.e(it, "Error in stream".appendThreadName())
                       })

        // Observable.just przyjmuje wartość, a to znaczy, że musi ona być wyliczona
        // w momencie tworzenia obiektu strumienia
        Observable.just(testNetworkDownload())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                           Timber.d("Result $it on ".appendThreadName())
                       }, {
                           Timber.e(it, "Error on".appendThreadName())
                       })
    }

    private fun createTestData(): List<Int> {
        Timber.d("Create stream on thread".appendThreadName())
        return listOf(1, 2, 3, 4)
    }

    private fun testNetworkDownload(): String {
        Timber.d("Do some network request on".appendThreadName())
        Thread.sleep(1000)
        return "42"
    }

    private fun String.appendThreadName() = this + " - ${Thread.currentThread().name}"

}