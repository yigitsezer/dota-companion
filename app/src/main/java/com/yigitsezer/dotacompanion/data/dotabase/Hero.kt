package com.yigitsezer.dotacompanion.data.dotabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
class Hero {
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "full_name")
    var fullName: String? = null

    @ColumnInfo(name = "media_name")
    var mediaName: String? = null

    @ColumnInfo(name = "localized_name")
    var localizedName: String? = null

    @ColumnInfo(name = "real_name")
    var realName: String? = null

    @ColumnInfo(name = "aliases")
    var aliases: String? = null

    @ColumnInfo(name = "roles")
    var roles: String? = null

    @ColumnInfo(name = "role_levels")
    var roleLevels: String? = null

    @ColumnInfo(name = "hype")
    var hype: String? = null

    @ColumnInfo(name = "bio")
    var bio: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "icon")
    var icon: String? = null

    @ColumnInfo(name = "portrait")
    var portrait: String? = null

    @ColumnInfo(name = "talents")
    var talents: String? = null

    @ColumnInfo(name = "color")
    var color: String? = null

    @ColumnInfo(name = "legs")
    var legs: Int? = null

    @ColumnInfo(name = "team")
    var team: String? = null

    @ColumnInfo(name = "base_health_regen")
    var baseHealthRegen: Float? = null

    @ColumnInfo(name = "base_mana_regen")
    var baseManaRegen: Float? = null

    @ColumnInfo(name = "base_movement")
    var baseMovement: Int? = null

    @ColumnInfo(name = "base_attack_speed")
    var baseAttackSpeed: Int? = null

    @ColumnInfo(name = "turn_rate")
    var turnRate: Float? = null

    @ColumnInfo(name = "base_armor")
    var baseArmor: Int? = null

    @ColumnInfo(name = "attack_range")
    var attackRange: Int? = null

    @ColumnInfo(name = "attack_projectile_speed")
    var attackProjectileSpeed: Int? = null

    @ColumnInfo(name = "attack_damage_min")
    var attackDamageMin: Int? = null

    @ColumnInfo(name = "attack_damage_max")
    var attackDamageMax: Int? = null

    @ColumnInfo(name = "attack_rate")
    var attackRate: Float? = null

    @ColumnInfo(name = "attack_point")
    var attackPoint: Float? = null

    @ColumnInfo(name = "attr_primary")
    var attrPrimary: String? = null

    @ColumnInfo(name = "attr_strength_base")
    var attrStrengthBase: Int? = null

    @ColumnInfo(name = "attr_strength_gain")
    var attrStrengthGain: Float? = null

    @ColumnInfo(name = "attr_intelligence_base")
    var attrIntelligenceBase: Int? = null

    @ColumnInfo(name = "attr_intelligence_gain")
    var attrIntelligenceGain: Float? = null

    @ColumnInfo(name = "attr_agility_base")
    var attrAgilityBase: Int? = null

    @ColumnInfo(name = "attr_agility_gain")
    var attrAgilityGain: Float? = null

    @ColumnInfo(name = "vision_day")
    var visionDay: Int? = null

    @ColumnInfo(name = "vision_night")
    var visionNight: Int? = null

    @ColumnInfo(name = "magic_resistance")
    var magicResistance: Int? = null

    @ColumnInfo(name = "is_melee")
    var melee: Int? = null
    set(value) {field = value}

    @ColumnInfo(name = "material")
    var material: String? = null

    @ColumnInfo(name = "json_data")
    var jsonData: String? = null
}