package com.yigitsezer.dotacompanion.views

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import com.yigitsezer.dotacompanion.R

class AbilityView(context: Context?) : LinearLayout(context) {
    var abilityImage: ImageView

    init {
        inflate(context, R.layout.ability_view, this)
        abilityImage = findViewById(R.id.ability_view_ability_icon)
    }
}