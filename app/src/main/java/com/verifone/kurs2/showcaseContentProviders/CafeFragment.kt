package com.verifone.kurs2.showcaseContentProviders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.verifone.kurs2.App
import com.verifone.kurs2.R
import com.verifone.kurs2.core.cafe.Cafe
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.presentation.CafeViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_cafe.*
import timber.log.Timber
import javax.inject.Inject

class CafeFragment : Fragment() {

    val disposables = CompositeDisposable()
    lateinit var viewModel: CafeViewModel

    @Inject
    lateinit var cafe: Cafe
    @Inject
    lateinit var getMood: GetMood
    @Inject
    lateinit var observeCoffeeIntake: ObserveCoffeeIntake
    @Inject
    lateinit var saveCoffeeIntake: SaveCoffeeIntake


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_cafe, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        viewModel = CafeViewModel(observeCoffeeIntake, saveCoffeeIntake, getMood)

        val dbDataStream = viewModel.observeCoffeeIntake()
            .subscribe({
                           data.text = it.joinToString(separator = "\n")
                       }, {
                           Timber.e(it, "Error while observing DB")
                       })
        disposables.add(dbDataStream)

        submit.setOnClickListener {
            val currentInput = amount.text.toString()
            val asFloat = currentInput.toFloatOrNull()
            if (asFloat == null) {
                Toast.makeText(context, "Wrong value", Toast.LENGTH_SHORT).show()
            } else {

                val stream = viewModel.saveCoffeeIntake(asFloat)
                    .subscribe()
                disposables.add(stream)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

}