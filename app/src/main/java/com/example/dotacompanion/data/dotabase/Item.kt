package com.example.dotacompanion.data.dotabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
class Item {
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "localized_name")
    var localized_name: String? = null

    @ColumnInfo(name = "aliases")
    var aliases: String? = null

    @ColumnInfo(name = "quality")
    var quality: String? = null

    @ColumnInfo(name = "icon")
    var icon: String? = null

    @ColumnInfo(name = "cost")
    var cost: Int? = null

    @ColumnInfo(name = "cooldown")
    var cooldown: String? = null

    @ColumnInfo(name = "cast_range")
    var cast_range: String? = null

    @ColumnInfo(name = "mana_cost")
    var mana_cost: String? = null

    @ColumnInfo(name = "base_level")
    var base_level: Int? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "lore")
    var lore: String? = null

    @ColumnInfo(name = "secret_shop")
    var secret_shop: Int? = null

    @ColumnInfo(name = "neutral_tier")
    var neutral_tier: String? = null

    @ColumnInfo(name = "ability_special")
    var ability_special: String? = null

    @ColumnInfo(name = "recipe")
    var recipe: String? = null

    @ColumnInfo(name = "json_data")
    var json_data: String? = null


}