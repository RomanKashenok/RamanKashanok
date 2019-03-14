package com.kashanok.classes.homework.common.workitem

import com.kashanok.classes.R
import java.io.Serializable

data class WorkNumber(
    val titleId: Int,
    val descId: Int,
    val imageResourceId: Int
) : Serializable

enum class HomeWorkNumber(val title: Int, val desc: Int, val image: Int) {
    FIRST(R.string.first, R.string.first_homework, R.drawable.icon_test),
    SECOND(R.string.second, R.string.second_homework, R.drawable.icon_test),
    THIRD(R.string.third, R.string.third_homework, R.drawable.icon_test),
    FOUR(R.string.four, R.string.four_homework, R.drawable.icon_test),
    FIVE(R.string.five, R.string.five_homework, R.drawable.icon_test),
    SIX(R.string.six, R.string.six_homework, R.drawable.icon_test);

    companion object {
        fun fromOrdinal(ordinal: Int) = values()[ordinal]
    }
}