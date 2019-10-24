package com.verifone.kurs2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_menu.button1
import kotlinx.android.synthetic.main.fragment_menu.button2
import kotlinx.android.synthetic.main.fragment_menu.button3
import kotlinx.android.synthetic.main.fragment_menu.show_animation

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button1.setOnClickListener {
            (activity as MainActivity).openServicesFragment()
        }

        button2.setOnClickListener {
            (activity as MainActivity).openReceiversFragment()
        }

        button3.setOnClickListener {
            (activity as MainActivity).openContentProvidersFragment()
        }

        show_animation.setOnClickListener {
            (activity as MainActivity).openAnimationFragment()
        }
        // Użycie lambdy z dwoma parametrami, oba argumenty muszą być nazwane
        // takesLambdaWithTwoParams { someValue, someString ->  }
    }

    // Przykład funkcji, która przyjmuje lambdę z dwoma parametrami
    /*private fun takesLambdaWithTwoParams(lambda: (Int, String) -> Unit) {

    }*/
}