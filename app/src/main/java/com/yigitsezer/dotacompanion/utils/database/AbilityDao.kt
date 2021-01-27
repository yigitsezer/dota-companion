package com.yigitsezer.dotacompanion.utils.database

import androidx.room.Dao
import androidx.room.Query
import com.yigitsezer.dotacompanion.data.dotabase.Ability

@Dao
interface AbilityDao {
    //@Query("SELECT * FROM abilities")
    //fun getAllAbilities(): List<Ability>

    @Query("SELECT * FROM abilities WHERE hero_id=:id AND slot IS NOT NULL AND (behavior NOT LIKE '%hidden%' AND behavior NOT LIKE '%not_learnable%' OR behavior IS NULL) AND scepter_grants = 0 ORDER BY slot")
    fun getHeroAbilities(id: Int): List<Ability>?

    @Query("SELECT * FROM abilities WHERE id=:id")
    fun getAbility(id: Int): Ability?

    @Query("SELECT * FROM items where name is :name")
    fun test(name: String): Int

    @Query("SELECT icon FROM items where id is :id")
    fun test2(id: Int): String
}