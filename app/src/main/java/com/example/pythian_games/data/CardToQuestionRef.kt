package com.example.pythian_games.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CardToQuestionRef(var cardNumber: Int, var questionNumber: Int): Parcelable {
    constructor(): this(0, 0)
}