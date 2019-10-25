package com.verifone.kurs2.showcaseContentProviders

import android.content.ContentProviderClient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.verifone.kurs2.App
import com.verifone.kurs2.R
import com.verifone.kurs2.core.cafe.Cafe
import com.verifone.kurs2.core.entity.CoffeeIntake
import com.verifone.kurs2.core.repository.AppDatabase
import com.verifone.kurs2.core.repository.CoffeeIntakeDao
import com.verifone.kurs2.core.repository.getDatabase
import com.verifone.kurs2.databinding.FragmentContentProvidersBinding
import com.verifone.kurs2.showcaseContentProviders.di.ContentProvidersFragmentComponent
import com.verifone.kurs2.showcaseContentProviders.di.ContentProvidersFragmentModule
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.presentation.ContentProvidersViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_content_providers.amount
import kotlinx.android.synthetic.main.fragment_content_providers.data
import kotlinx.android.synthetic.main.fragment_content_providers.submit
import timber.log.Timber
import java.io.File.separator
import javax.inject.Inject

class ContentProvidersFragment : Fragment() {

    lateinit var viewModel: ContentProvidersViewModel

    @Inject
    lateinit var cafe: Cafe
    @Inject
    lateinit var observeCoffeeIntake: ObserveCoffeeIntake
    @Inject
    lateinit var saveCoffeeIntake : SaveCoffeeIntake
    @Inject
    lateinit var getMood : GetMood
    @Inject
    lateinit var coffeeIntakeDao: CoffeeIntakeDao

    lateinit var binding: FragmentContentProvidersBinding

    var model : ObservableField<String> = ObservableField("Empty state")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView::ContentProvidersFragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content_providers, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.plusContentProvidersFragmentComponent(ContentProvidersFragmentModule()).inject(this)
        Timber.d("onViewCreated::ContentProvidersFragment")
        Timber.d("Fragment dostal ${this.cafe}")

        viewModel = ViewModelProviders.of(this)[ContentProvidersViewModel::class.java].also {
            it.init(observeCoffeeIntake, saveCoffeeIntake,getMood)
        }

        binding.setData(viewModel.data())
        binding.lifecycleOwner = this
        binding.setSubmit {
            val currentInput = amount.text.toString()
            val asFloat = currentInput.toFloatOrNull()
            if (asFloat == null) {
                Toast.makeText(context, "Wrong value", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveCoffeeIntake(asFloat)
            }
        }

/*        viewModel.observeCoffeeIntake().observe(this, Observer<List<CoffeeIntake>> {
            model.set(it.joinToString(separator = "\n"))
        })*/
    }
}