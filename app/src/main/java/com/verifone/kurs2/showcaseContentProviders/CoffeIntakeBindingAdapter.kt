package com.verifone.kurs2.showcaseContentProviders

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.verifone.kurs2.core.entity.CoffeeIntake
import java.io.File.separator

@BindingAdapter("coffeIntakes")
fun setConfeIntakes(view: TextView, coffeIntakes: List<CoffeeIntake>?){
    view.text = coffeIntakes?.joinToString(separator = "\n") ?: "Empty state"
}