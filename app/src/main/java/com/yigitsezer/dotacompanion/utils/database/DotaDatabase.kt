package com.yigitsezer.dotacompanion.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yigitsezer.dotacompanion.data.dotabase.Ability
import com.yigitsezer.dotacompanion.data.dotabase.Hero
import com.yigitsezer.dotacompanion.data.dotabase.Item

@Database(entities = [Hero::class, Ability::class, Item::class], version = 1, exportSchema = true)
abstract class DotaDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun abilityDao(): AbilityDao
    abstract fun itemDao(): ItemDao
}