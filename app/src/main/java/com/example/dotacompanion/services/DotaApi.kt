package com.example.dotacompanion.services

import com.example.dotacompanion.data.HeroSummaryData
import com.example.dotacompanion.data.MatchSummaryData
import com.example.dotacompanion.data.Player
import com.example.dotacompanion.data.PlayerWl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DotaApi {
    //Either to show most successful heroes for the player
    //or to show them all (maybe not?)
    @GET("players/{user}/heroes")
    fun getHeroes(@Path("user") user: String?): Call<List<HeroSummaryData?>?>?

    //Either for recent matches with limit
    //and for a page with numbers, maybe a long list?
    @GET("players/{user}/matches")
    fun getMatchSummary(@Path("user") user: String?, @Query("limit") limit: Int, @Query("offset") offset: Int): Call<List<MatchSummaryData?>?>?

    @GET("players/{user}")
    fun getPlayerData(@Path("user") user: String?): Call<Player?>?

    @GET("players/{user}/wl")
    fun getPlayerWl(@Path("user") user: String?): Call<PlayerWl?>?
}