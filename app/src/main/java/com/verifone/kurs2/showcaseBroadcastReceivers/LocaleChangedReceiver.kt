package com.verifone.kurs2.showcaseBroadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.verifone.kurs2.App
import timber.log.Timber

class LocaleChangedReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
        Timber.d("Locale has changed $intent")
    }
}