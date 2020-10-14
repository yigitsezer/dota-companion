package com.example.dotacompanion.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.dotacompanion.R
import java.util.*

/**
 * Carry
 * Support
 * Nuker
 * Disabler
 * Jungler
 * Durable
 * Escape
 * Pusher
 * Initiator
 */
class RoleProgressView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var level = 0
    var roleName: String? = null
    private var bars: MutableList<View>? = null
    private fun updateLevelDisplay() {
        bars?.let {

        }
        if (bars == null) {
            bars = ArrayList()
            bars?.add(findViewById(R.id.bar_1))
            bars?.add(findViewById(R.id.bar_2))
            bars?.add(findViewById(R.id.bar_3))
        }
        for (i in 0 until level % 4) {
            bars!![i].setBackgroundResource(R.drawable.role_level_box_filled)
        }
    }

    fun getLevel(): Int {
        return level
    }

    fun setLevel(level: Int) {
        this.level = level
        updateLevelDisplay()
    }

    init {
        inflate(context, R.layout.role_progress_view, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoleProgressView)
        level = attributes.getInteger(R.styleable.RoleProgressView_level, 0)
        updateLevelDisplay() //remove this if you dont think you will use level attribute in xml
        attributes.recycle()
    }
}