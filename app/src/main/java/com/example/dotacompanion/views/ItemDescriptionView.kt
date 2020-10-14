package com.example.dotacompanion.views

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.text.Spanned
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotacompanion.R
import com.example.dotacompanion.fragments.encyclopedia.items.ItemListAdapter

class ItemDescriptionView : LinearLayout {
    private var itemImage: ImageView
    private var itemName: TextView
    private var descriptionRecyclerView: RecyclerView

    constructor(context: Context?) : super(context) {
        inflate(context, R.layout.item_description_view, this)
        itemImage = findViewById(R.id.description_item_image)
        itemName = findViewById(R.id.description_item_name)
        descriptionRecyclerView = findViewById(R.id.description_recycler_view)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context, R.layout.item_view, this)
        itemImage = findViewById(R.id.description_item_image)
        itemName = findViewById(R.id.description_item_name)
        descriptionRecyclerView = findViewById(R.id.description_recycler_view)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemDescriptionView)
        itemImage.setImageDrawable(attributes.getDrawable(R.styleable.ItemDescriptionView_item_desc_image))
        attributes.recycle()
    }

    fun setItemName(name: String) {
        itemName.text = name
    }


    fun setIconImage(resId: Int) {
        itemImage.setImageResource(resId)
        //itemImage.setBackgroundResource(resId)
    }

    fun setDescription(itemId: Int) {
        //descriptionRecyclerView.layoutManager = GridLayoutManager(this.context, 1, RecyclerView.VERTICAL, false)
        //descriptionRecyclerView.adapter = ItemDescriptionAdapter(context, itemId)
    }
}