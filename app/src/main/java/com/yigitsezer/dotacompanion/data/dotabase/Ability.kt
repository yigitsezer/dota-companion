package com.yigitsezer.dotacompanion.data.dotabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abilities")
class Ability {
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "hero_id")
    var heroId: Int? = null

    @ColumnInfo(name = "behavior")
    var behavior: String? = null

    @ColumnInfo(name = "damage_type")
    var damageType: String? = null

    @ColumnInfo(name = "spell_immunity")
    var spellImmunity: String? = null

    @ColumnInfo(name = "target_team")
    var targetTeam: String? = null

    @ColumnInfo(name = "dispellable")
    var dispellable: String? = null

    @ColumnInfo(name = "cast_range")
    var castRange: String? = null

    @ColumnInfo(name = "cast_point")
    var castPoint: String? = null

    @ColumnInfo(name = "channel_time")
    var channelTime: String? = null

    @ColumnInfo(name = "cooldown")
    var cooldown: String? = null

    @ColumnInfo(name = "duration")
    var duration: String? = null

    @ColumnInfo(name = "damage")
    var damage: String? = null

    @ColumnInfo(name = "mana_cost")
    var manaCost: String? = null

    @ColumnInfo(name = "charges")
    var charges: String? = null

    @ColumnInfo(name = "ability_special")
    var abilitySpecial: String? = null

    @ColumnInfo(name = "linked_abilities")
    var linkedAbilities: String? = null

    @ColumnInfo(name = "talent_slot")
    var talentSlot: Int? = null

    @ColumnInfo(name = "ability_slot")
    var abilitySlot: Int? = null

    @ColumnInfo(name = "icon")
    var icon: String? = null

    @ColumnInfo(name = "localized_name")
    var localizedName: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "lore")
    var lore: String? = null

    @ColumnInfo(name = "note")
    var note: String? = null

    @ColumnInfo(name = "aghanim")
    var aghanim: String? = null

    @ColumnInfo(name = "aghanim_grants")
    var aghanimGrants: Int? = null

    @ColumnInfo(name = "json_data")
    var jsonData: String? = null
}