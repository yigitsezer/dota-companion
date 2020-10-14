package com.example.dotacompanion.utils.database

import androidx.room.Dao
import androidx.room.Query
import com.example.dotacompanion.data.dotabase.Item
import com.example.dotacompanion.data.dotabase.json.ItemSpecial

@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE id=:id")
    fun getItem(id: Int): Item
}