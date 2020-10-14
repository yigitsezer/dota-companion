package com.example.dotacompanion.services

import retrofit2.Call
import retrofit2.http.GET

interface SteamApi {
    @get:GET("steamid")
    val steamID: Call<List<SteamIDResponse?>?>?
}