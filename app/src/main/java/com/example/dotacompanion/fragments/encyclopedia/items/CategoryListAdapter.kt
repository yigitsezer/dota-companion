package com.example.dotacompanion.fragments.encyclopedia.items

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dotacompanion.DotaApplication
import com.example.dotacompanion.utils.Constants
import com.example.dotacompanion.utils.ItemCategory
import com.example.dotacompanion.views.ItemCategoryView

class CategoryListAdapter(private val context: Context?, private val itemCategory: ItemCategory?, private val navController: NavController) : RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {
    private var subCategoryItems: Array<IntArray>
    private var subCategoryItemNames: Array<String>
    private var icons: Array<IntArray>

    class CategoryViewHolder(var categoryView: ItemCategoryView) : RecyclerView.ViewHolder(categoryView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CategoryViewHolder {
        val v = ItemCategoryView(parent.context)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryView.setCategoryName(subCategoryItemNames.get(position))
        holder.categoryView.setRecyclerView(subCategoryItems.get(position), icons[position], navController)
        holder.categoryView.setOnClickListener {
            //val bundle = Bundle()
            //bundle.putInt("item_id", itemIds[position])
            //navController?.navigate(R.id.action_heroesFragment_to_heroInfoFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return subCategoryItems.size
    }

    init {
        when (this.itemCategory) {
            //TODO: I mean this is bullshit
            ItemCategory.BASICS -> {
                subCategoryItems = arrayOf(Constants.ITEMS_CONSUMABLES, Constants.ITEMS_ATTRIBUTES, Constants.ITEMS_EQUIPMENT, Constants.ITEMS_MISCELLANEOUS, Constants.ITEMS_SECRET_SHOP)
                icons = arrayOf(Constants.ITEMS_ICONS_CONSUMABLES, Constants.ITEMS_ICONS_ATTRIBUTES, Constants.ITEMS_ICONS_EQUIPMENT, Constants.ITEMS_ICONS_MISCELLANEOUS, Constants.ITEMS_ICONS_SECRET_SHOP)
                subCategoryItemNames = Constants.ITEMS_BASICS
            }
            ItemCategory.UPGRADES -> {
                subCategoryItems = arrayOf(Constants.ITEMS_ACCESSORIES, Constants.ITEMS_SUPPORT, Constants.ITEMS_MAGICAL, Constants.ITEMS_ARMOR, Constants.ITEMS_WEAPONS, Constants.ITEMS_ARTIFACTS)
                icons = arrayOf(Constants.ITEMS_ICONS_ACCESSORIES, Constants.ITEMS_ICONS_SUPPORT, Constants.ITEMS_ICONS_MAGICAL, Constants.ITEMS_ICONS_ARMOR, Constants.ITEMS_ICONS_WEAPONS, Constants.ITEMS_ICONS_ARTIFACTS)
                subCategoryItemNames = Constants.ITEMS_UPGRADES
            }
            ItemCategory.NEUTRAL_ITEMS -> {
                subCategoryItems = arrayOf(Constants.ITEMS_TIER_1, Constants.ITEMS_TIER_2, Constants.ITEMS_TIER_3, Constants.ITEMS_TIER_4, Constants.ITEMS_TIER_5)
                icons = arrayOf(Constants.ITEMS_ICONS_TIER_1, Constants.ITEMS_ICONS_TIER_2, Constants.ITEMS_ICONS_TIER_3, Constants.ITEMS_ICONS_TIER_4, Constants.ITEMS_ICONS_TIER_5)
                subCategoryItemNames = Constants.ITEMS_NEUTRAL
            }
            else -> {
                subCategoryItems = arrayOf(Constants.ITEMS_CONSUMABLES, Constants.ITEMS_ATTRIBUTES)
                icons = arrayOf(Constants.ITEMS_ICONS_CONSUMABLES, Constants.ITEMS_ICONS_ATTRIBUTES)
                subCategoryItemNames = Constants.ITEMS_BASICS
            }
        }
    }
}