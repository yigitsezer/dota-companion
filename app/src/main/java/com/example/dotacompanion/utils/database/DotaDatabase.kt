package com.example.dotacompanion.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dotacompanion.data.dotabase.Hero
import com.example.dotacompanion.data.dotabase.Item

@Database(entities = [Hero::class, Item::class], version = 1, exportSchema = true)
abstract class DotaDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    //abstract fun abilityDao(): AbilityDao
    abstract fun itemDao(): ItemDao
}