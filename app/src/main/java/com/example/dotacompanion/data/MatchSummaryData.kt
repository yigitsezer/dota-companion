package com.example.dotacompanion.data

// players/{account_id}/matches
class MatchSummaryData(val match_id: Long, val player_slot: Int, val isRadiant_win: Boolean, val duration: Int, val game_mode: Int, val lobby_type: Int, val hero_id: Int, val start_time: Int, val version: Int, val kills: Int, val deaths: Int, val assists: Int, val skill: Int, val party_size: Int)