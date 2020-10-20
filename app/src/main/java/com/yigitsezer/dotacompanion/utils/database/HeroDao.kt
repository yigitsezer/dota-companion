package com.yigitsezer.dotacompanion.utils.database

import androidx.room.Dao
import androidx.room.Query
import com.yigitsezer.dotacompanion.data.dotabase.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes WHERE id=:id")
    fun getHeroById(id: Int): Hero?
}