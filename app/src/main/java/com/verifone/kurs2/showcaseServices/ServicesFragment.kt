package com.verifone.kurs2.showcaseServices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.verifone.kurs2.R
import com.verifone.kurs2.core.repository.getDatabase
import timber.log.Timber
import java.lang.IllegalStateException

class ServicesFragment : Fragment() {

    private var service: PrintLogService? = null
    private val connection = object: ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            Timber.d("Service disconnected")
            service = null
        }

        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            Timber.d("Service connected")
            service = (binder as PrintLogService.LocalBinder).service
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_services, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.bindService(
            Intent(context, PrintLogService::class.java),
            connection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        context?.unbindService(connection)
        // onServiceDisconnected jest wywoływany w patologicznych sytuacjach,
        // zwykły unbindService nie powoduje jego wywołania
        service = null
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(context, PrintLogService::class.java)
        // KOTLIN STYLE
        context?.startService(intent) ?: throw IllegalStateException("Context is null")

        /* JAVA STYLE
        val contextCopy = context
        if (contextCopy != null) {
            contextCopy.startService(intent)
        } else {
            throw IllegalStateException()
        }
        */

    }
}