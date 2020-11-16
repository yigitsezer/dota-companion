package com.yigitsezer.dotacompanion.views

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.yigitsezer.dotacompanion.R


class ItemDescriptionSingleView(context: Context?) : LinearLayout(context) {
    private var descriptionText: TextView


    init {
        inflate(context, R.layout.item_description_single, this)
        descriptionText = findViewById(R.id.item_desc_single_text)
    }

    fun setDescriptionText(str: String) {
        descriptionText.text = HtmlCompat.fromHtml(str.replace(Regex("\\n"), "<br>"), HtmlCompat.FROM_HTML_MODE_LEGACY)
        //descriptionText.text = str
    }
}