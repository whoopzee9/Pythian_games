package com.example.pythian_games.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(var id: String, var name: String, var email: String/*, var games: ArrayList<Game>*/): Parcelable {
}