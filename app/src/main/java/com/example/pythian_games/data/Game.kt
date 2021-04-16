package com.example.pythian_games.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Game(var id: Int,
           var name: String,
           var password: String,
           var players: ArrayList<String>,
           var isPlaying: Boolean,
           var isAnswering: Boolean,
           var currentCardNumber: Int,
           var cleaningQuestions: ArrayList<CleaningQuestion>,
           var questionCardsPool: ArrayList<CardToQuestionRef>): Parcelable {

    constructor(): this(0, "", "", ArrayList(), false, false, 0, ArrayList(), ArrayList())
}