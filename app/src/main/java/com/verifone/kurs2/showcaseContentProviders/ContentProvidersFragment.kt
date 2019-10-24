package com.verifone.kurs2.showcaseContentProviders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.verifone.kurs2.App
import com.verifone.kurs2.R
import com.verifone.kurs2.core.cafe.Cafe
import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.core.repository.getDatabase
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.presentation.ContentProvidersViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_content_providers.amount
import kotlinx.android.synthetic.main.fragment_content_providers.data
import kotlinx.android.synthetic.main.fragment_content_providers.submit
import timber.log.Timber
import javax.inject.Inject

class ContentProvidersFragment : Fragment() {

    val disposables = CompositeDisposable()
    lateinit var viewModel: ContentProvidersViewModel

    @Inject
    lateinit var cafe: Cafe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_content_providers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = context?.getDatabase() ?: throw IllegalStateException("Bazinga")

        val dao = db.coffeeIntakeDao()
        App.container[AppDatabase::class.java] = db

        App.appComponent.inject(this)
        Timber.d("Fragment dostal ${this.cafe}")
        val observeCoffeeIntake = ObserveCoffeeIntake(dao)
        val saveCoffeeIntake = SaveCoffeeIntake(dao)
        val getMood = GetMood()
        viewModel = ContentProvidersViewModel(observeCoffeeIntake, saveCoffeeIntake,getMood)
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