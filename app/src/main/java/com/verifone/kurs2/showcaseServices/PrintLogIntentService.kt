package com.verifone.kurs2.showcaseServices

import android.app.IntentService
import android.content.Intent
import timber.log.Timber

class PrintLogIntentService : IntentService("print log service") {

    // intenty są kolejkowane i po kolei obsługiwane
    override fun onHandleIntent(p0: Intent?) {
        // onHandleIntent jest wywoływany na innym wątku niż wątek główny
        Timber.d("Execute some task")
    }
}