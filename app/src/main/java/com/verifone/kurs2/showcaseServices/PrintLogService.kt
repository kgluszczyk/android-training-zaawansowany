package com.verifone.kurs2.showcaseServices

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.Binder
import android.os.IBinder
import timber.log.Timber

class PrintLogService : Service() {

    private val binder = LocalBinder()

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("On create")
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        Timber.d("On start command ${Thread.currentThread().name}")
        PrintLogTask().execute()
        // do wystartowania serwisu w trybie foreground używamy startForeground
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("On destroy")
    }

    private inner class PrintLogTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            Timber.d("Go sleep")
            Thread.sleep(10000)
            Timber.d("I'm awake")
        }

        override fun onPostExecute(result: Unit?) {
            Timber.d("onPostExecute")
            stopSelf()
        }
    }

    inner class LocalBinder : Binder() {
        val service = this@PrintLogService
    }
}