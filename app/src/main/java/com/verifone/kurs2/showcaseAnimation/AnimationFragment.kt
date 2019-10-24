package com.verifone.kurs2.showcaseAnimation

import android.Manifest
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.verifone.kurs2.R
import kotlinx.android.synthetic.main.fragment_animation.animation_root
import kotlinx.android.synthetic.main.fragment_animation.constraint_animation
import kotlinx.android.synthetic.main.fragment_animation.image
import kotlinx.android.synthetic.main.fragment_animation.request_permission
import kotlinx.android.synthetic.main.fragment_animation.single_view_animation
import timber.log.Timber

class AnimationFragment : Fragment() {

    private lateinit var currentConstraints: ConstraintSet
    private lateinit var startConstraints: ConstraintSet
    private lateinit var endConstraints: ConstraintSet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_animation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        request_permission.setOnClickListener {
            val isPermissionGranted =
                PermissionChecker.checkSelfPermission(
                    context!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PermissionChecker.PERMISSION_GRANTED
            Timber.d("Is permission granted? $isPermissionGranted")

            if (!isPermissionGranted) {
                // shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                Timber.d("Permission is not granted, request permission")
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 666)
            }
        }

        single_view_animation.setOnClickListener {

            val animator = image.animate()
                .translationXBy(200f)
                .alpha(0.7f)
                .scaleXBy(1.2f)
                .scaleYBy(1.2f)

            animator.interpolator = AccelerateDecelerateInterpolator()
            animator.duration = 800
            animator.start()
        }

        startConstraints = ConstraintSet()
        startConstraints.clone(context, R.layout.fragment_animation)
        endConstraints = ConstraintSet()
        endConstraints.clone(context, R.layout.fragment_animation_changed)

        currentConstraints = startConstraints

        constraint_animation.setOnClickListener {

            val destConstraints = if (currentConstraints == startConstraints) {
                endConstraints
            } else {
                startConstraints
            }

            TransitionManager.beginDelayedTransition(animation_root)
            destConstraints.applyTo(animation_root)
            currentConstraints = destConstraints
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Timber.d("Request code $requestCode permissions $permissions results $grantResults")
    }

}