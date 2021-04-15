package com.example.pythian_games.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CleaningQuestion(
    var question: Question,
    var cardNumber: Int,
    var answersChosen: ArrayList<Int>): Parcelable {

    constructor() : this(Question(), 0, ArrayList())
}