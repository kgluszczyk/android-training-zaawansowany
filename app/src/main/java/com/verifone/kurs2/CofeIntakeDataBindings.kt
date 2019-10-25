package com.verifone.kurs2

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.verifone.kurs2.core.entity.CoffeeIntake

@BindingAdapter("cofeIntakes")
fun setCofeIntakes(view: TextView, coffeIntakes: List<CoffeeIntake>?){
    view.text = coffeIntakes?.joinToString(separator = "\n") ?: "Default"
}