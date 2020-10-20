package com.yigitsezer.dotacompanion.utils.database

import androidx.room.Dao
import androidx.room.Query
import com.yigitsezer.dotacompanion.data.dotabase.Ability

@Dao
interface AbilityDao {
    //@Query("SELECT * FROM abilities")
    //fun getAllAbilities(): List<Ability>

    @Query("SELECT * FROM abilities WHERE hero_id=:id")
    fun test(id: Int): List<Ability>?
}