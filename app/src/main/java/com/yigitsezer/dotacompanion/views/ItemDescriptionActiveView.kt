package com.yigitsezer.dotacompanion.views

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.yigitsezer.dotacompanion.R


class ItemDescriptionActiveView(context: Context?) : LinearLayout(context) {
    private var activeText: TextView
    private var descriptionText: TextView
    private var cooldownText: TextView
    private var manacostText: TextView


    init {
        inflate(context, R.layout.item_description_active, this)
        activeText = findViewById(R.id.item_desc_active_name)
        descriptionText = findViewById(R.id.item_desc_active_text)
        cooldownText = findViewById(R.id.item_desc_active_cd_text)
        manacostText = findViewById(R.id.item_desc_active_mana_text)
    }

    fun setActiveText(str: String) {
        activeText.text = str
    }

    fun setDescriptionText(str: String) {
        descriptionText.text = HtmlCompat.fromHtml(str.replace(Regex("\\n"), "<br>"), HtmlCompat.FROM_HTML_MODE_LEGACY)
        //descriptionText.text = str
    }

    fun setCooldownText(str: String) {
        cooldownText.text = str
    }

    fun setManacostText(str: String) {
        manacostText.text = str
    }

    fun removeManacost() {
        findViewById<ImageView>(R.id.item_desc_active_mana_icon).visibility = View.GONE
        findViewById<TextView>(R.id.item_desc_active_mana_text).visibility = View.GONE
    }

    fun removeCooldown() {
        findViewById<ImageView>(R.id.item_desc_active_cd_icon).visibility = View.GONE
        findViewById<TextView>(R.id.item_desc_active_cd_text).visibility = View.GONE
    }
}