package com.verifone.kurs2

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.verifone.kurs2.showcaseAnimation.AnimationFragment
import com.verifone.kurs2.showcaseBroadcastReceivers.ReceiversFragment
import com.verifone.kurs2.showcaseContentProviders.ContentProvidersFragment
import com.verifone.kurs2.showcaseServices.ServicesFragment
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var contentFrameLayout: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // pobranie referencji do widoku - "stara metoda", ale bezpieczna
        // findViewById<FrameLayout>(R.id.content)
        // contentFrameLayout = findViewById(R.id.content)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, MenuFragment())
                .commit()
        }
    }

    fun openServicesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ServicesFragment())
            .addToBackStack(null)
            .commit()
    }

    fun openReceiversFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ReceiversFragment())
            .addToBackStack(null)
            .commit()
    }

    fun openContentProvidersFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ContentProvidersFragment())
            .addToBackStack(null)
            .commit()
    }


    fun openAnimationFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, AnimationFragment())
            .addToBackStack(null)
            .commit()
    }
}
