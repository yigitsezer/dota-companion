package com.example.dotacompanion.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotacompanion.R
import com.example.dotacompanion.fragments.encyclopedia.items.ItemListAdapter

class ItemCategoryView : LinearLayout {
    private var categoryName: TextView
    private var recyclerView: RecyclerView

    constructor(context: Context?) : super(context) {
        inflate(context, R.layout.item_category_view, this)
        categoryName = findViewById(R.id.category_name)
        recyclerView = findViewById(R.id.items_recycler_view)
    }

    //xml constructor
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context, R.layout.item_category_view, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemCategoryView)
        categoryName = findViewById(R.id.category_name)
        recyclerView = findViewById(R.id.items_recycler_view)
        categoryName.text = attributes.getString(R.styleable.ItemCategoryView_category_name)
        attributes.recycle()
    }

    fun setRecyclerView(itemSubCategory: IntArray, icons: IntArray, navController: NavController) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context, 6, RecyclerView.VERTICAL, false)
        recyclerView.adapter = ItemListAdapter(context, itemSubCategory, icons, navController)
    }

    fun setCategoryName(name: CharSequence?) {
        this.categoryName.text = name
    }
}