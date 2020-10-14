package com.example.dotacompanion.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dotacompanion.R
import com.example.dotacompanion.databinding.EncyclopediaItemViewBinding
import com.google.android.material.card.MaterialCardView

class EncyclopediaItemView(context: Context, attrs: AttributeSet?) : MaterialCardView(context, attrs) {
    var imageView: ImageView
    var titleTextView: TextView
    var textTextView: TextView

    init {
        inflate(context, R.layout.encyclopedia_item_view, this)
        imageView = findViewById(R.id.card_image)
        titleTextView = findViewById(R.id.card_title)
        textTextView = findViewById(R.id.card_text)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EncyclopediaItemView)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.EncyclopediaItemView_card_image))
        titleTextView.text = attributes.getString(R.styleable.EncyclopediaItemView_card_title)
        textTextView.text = attributes.getString(R.styleable.EncyclopediaItemView_card_text)
        setCardBackgroundColor(Color.TRANSPARENT);
        setCardElevation(0F)
        attributes.recycle()
    }
}