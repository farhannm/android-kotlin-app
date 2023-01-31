package com.farhan.movieapp.Service

import com.farhan.movieapp.Models.TvResponse
import retrofit2.Call
import retrofit2.http.GET

interface TvApiInterface {
    @GET("/3/tv/popular?api_key=fed1d7df98e0d590b98e0b0115ec2e03")
    fun getTvList() : Call<TvResponse>
}