package com.yigitsezer.dotacompanion.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.yigitsezer.dotacompanion.R

class ItemView : LinearLayout {
    private var itemImage: ImageView

    constructor(context: Context?) : super(context) {
        inflate(context, R.layout.item_view, this)
        itemImage = findViewById(R.id.item_icon)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context, R.layout.item_view, this)
        itemImage = findViewById(R.id.item_icon)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemView)
        itemImage.setImageDrawable(attributes.getDrawable(R.styleable.ItemView_item_image))
        //itemImage.background = attributes.getDrawable(R.styleable.ItemView_item_image)
        attributes.recycle()
    }

    //TODO: IMAGES ARE OF DIFFERENT SIZES, RESIZE ALL TO 88x64
    fun setIconImage(resId: Int) {
        itemImage.setImageResource(resId)
        //itemImage.setBackgroundResource(resId)
    }
}