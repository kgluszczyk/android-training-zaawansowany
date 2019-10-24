package com.verifone.kurs2.showcaseContentProviders.domain

import com.verifone.kurs2.core.entity.Mood
import com.verifone.kurs2.core.entity.MoodExt
import kotlin.random.Random

class GetMood {

    fun execute(amount: Float) = when (amount.toInt()){
        in 5..10 -> Mood.GOOD_ENOUGH
        in 11..100 -> Mood.GOOD
        else -> Mood.BAD
    }
    fun execute() = when (val value = (1..3).shuffled().first()) {
        1 -> MoodExt.Sadness(Random.nextBoolean())
        2 -> MoodExt.Euphory("$value")
        else -> MoodExt.UNKNOWN
    }

}