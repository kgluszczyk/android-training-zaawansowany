package com.verifone.kurs2.showcaseContentProviders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.verifone.kurs2.App
import com.verifone.kurs2.R
import com.verifone.kurs2.core.cafe.Cafe
import com.verifone.kurs2.databinding.FragmentCafeBinding
import com.verifone.kurs2.showcaseContentProviders.domain.GetMood
import com.verifone.kurs2.showcaseContentProviders.domain.ObserveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.domain.SaveCoffeeIntake
import com.verifone.kurs2.showcaseContentProviders.presentation.CafeViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_cafe.*
import timber.log.Timber
import javax.inject.Inject

class CafeFragment : Fragment() {

    lateinit var viewModel: CafeViewModel

    @Inject
    lateinit var cafe: Cafe
    @Inject
    lateinit var getMood: GetMood
    @Inject
    lateinit var observeCoffeeIntake: ObserveCoffeeIntake
    @Inject
    lateinit var saveCoffeeIntake: SaveCoffeeIntake

    lateinit var binding: FragmentCafeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cafe, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this)[CafeViewModel::class.java].also {
            it.init(observeCoffeeIntake, saveCoffeeIntake, getMood)
        }
        binding.lifecycleOwner = this
        binding.setData(viewModel.observeCoffeeIntake())
        binding.setSubmit {
            val currentInput = amount.text.toString()
            val asFloat = currentInput.toFloatOrNull()
            if (asFloat == null) {
                Toast.makeText(context, "Wrong value", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveCoffeeIntake(asFloat)
            }
        }
    }
}