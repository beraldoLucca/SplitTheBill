package com.example.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    var name: String,
    var paidValue: Double,
    var valueToPay: Double?,
    var valueToReceive: Double?,
    var description: String,
): Parcelable
