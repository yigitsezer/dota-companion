package com.yigitsezer.dotacompanion.views

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.yigitsezer.dotacompanion.R


class ItemDescriptionPassiveView : LinearLayout {
    private var activeText: TextView
    private var descriptionText: TextView


    constructor(context: Context?) : super(context) {
        inflate(context, R.layout.item_description_passive, this)
        activeText = findViewById(R.id.item_desc_passive_name)
        descriptionText = findViewById(R.id.item_desc_passive_text)
    }

    fun setActiveText(str: String) {
        activeText.text = str
    }

    fun setDescriptionText(str: String) {
        descriptionText.text = HtmlCompat.fromHtml(str.replace(Regex("\\n"), "<br>"), HtmlCompat.FROM_HTML_MODE_LEGACY)
        //descriptionText.text = str
    }
}