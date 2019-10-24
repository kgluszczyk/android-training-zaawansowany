package com.verifone.kurs2.showcaseBroadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.verifone.kurs2.ACTION_IN_APP_MESSAGE
import com.verifone.kurs2.R
import kotlinx.android.synthetic.main.fragment_receivers.local_broadcast
import kotlinx.android.synthetic.main.fragment_receivers.system_broadcast
import timber.log.Timber

class ReceiversFragment : Fragment() {

    private val airplaneModeChangedReceiver = AirplaneModeChangeReceiver()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_receivers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.registerReceiver(
            airplaneModeChangedReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )

        system_broadcast.setOnClickListener {
            val intent = Intent(ACTION_IN_APP_MESSAGE)
            intent.putExtra("source", "system")
            context?.sendBroadcast(intent)
        }

        local_broadcast.setOnClickListener {
            val intent = Intent(ACTION_IN_APP_MESSAGE)
            intent.putExtra("source", "local")
            // Tutaj byłby wykorzystany LocalBroadcastManager, ale niestety w API 29 klasa ta została
            // wyeliminowana
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        context?.unregisterReceiver(airplaneModeChangedReceiver)
    }

    private class AirplaneModeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Timber.d("Airplane mode toggle changed")
        }
    }

}