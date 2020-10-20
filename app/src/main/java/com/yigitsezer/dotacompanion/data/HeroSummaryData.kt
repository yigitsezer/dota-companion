package com.yigitsezer.dotacompanion.data

// players/{account_id}/heroes
class HeroSummaryData(val hero_id: String, val last_played: Int, val games: Int, val win: Int, val with_games: Int, val with_win: Int, val against_games: Int, val against_win: Int)