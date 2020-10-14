package com.example.dotacompanion.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object JsonMapper {
    fun parse(json: String?): Map<String, Hero> {
        val gson = Gson()
        val type = object : TypeToken<Map<String?, Hero?>?>() {}.type
        val map = gson.fromJson<Map<String, Hero>>(json, type)
        gson.fromJson<Any>(json, type)
        return map
    }

    fun parse(context: Context, resId: Int): Map<String, Hero> {
        val gson = Gson()
        val json = getJson(context, resId)
        val type = object : TypeToken<Map<String?, Hero?>?>() {}.type
        val map = gson.fromJson<Map<String, Hero>>(json, type)
        gson.fromJson<Any>(json, type)
        return map
        //return gson.fromJson(json, Hero.class);
    }

    private fun getJson(context: Context, resId: Int): String {
        val sc = Scanner(context.resources.openRawResource(resId))
        val sb = StringBuffer()
        while (sc.hasNext()) {
            sb.append(sc.nextLine())
        }
        return sb.toString()
    }
}