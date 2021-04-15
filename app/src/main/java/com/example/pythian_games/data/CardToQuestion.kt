package com.example.pythian_games.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CardToQuestion(var cardNumber: Int, var question: Question) :Parcelable {
    constructor() : this(0, Question())
}