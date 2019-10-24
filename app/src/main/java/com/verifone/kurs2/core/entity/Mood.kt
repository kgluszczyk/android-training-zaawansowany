package com.verifone.kurs2.core.entity

enum class Mood {
    BAD, GOOD_ENOUGH, GOOD
}

sealed class MoodExt(val mood: Mood){
    object UNKNOWN : MoodExt(Mood.BAD)
    data class Euphory(val praise:String) : MoodExt(Mood.GOOD)
    class Sadness(val wasBetterBefore: Boolean): MoodExt(Mood.GOOD_ENOUGH)
}