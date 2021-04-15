package com.example.pythian_games.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Question(
    var questionId: Int,
    var questionText: String,
    var answer1: String,
    var answer2: String,
    var answer3: String,
    var answer4: String,
    var correctAns: Int,
    var questionTier: Int) :Parcelable {

   constructor() : this(0, "", "", "", "", "", 0, 0)
}