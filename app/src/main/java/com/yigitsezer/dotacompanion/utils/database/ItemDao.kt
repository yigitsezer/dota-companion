package com.yigitsezer.dotacompanion.utils.database

import androidx.room.Dao
import androidx.room.Query
import com.yigitsezer.dotacompanion.data.dotabase.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE id=:id")
    fun getItem(id: Int): Item
}