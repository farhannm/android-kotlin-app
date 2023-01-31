package com.farhan.movieapp.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvResponse(
    @SerializedName("results")
    val result_tv : List<Tv>
) : Parcelable{
    constructor() : this(mutableListOf())
}
