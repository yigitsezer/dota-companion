package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.R
import com.yigitsezer.dotacompanion.fragments.encyclopedia.items.ItemListAdapter.ItemViewHolder
import com.yigitsezer.dotacompanion.views.ItemView

class ItemListAdapter(private val context: Context?, private val itemIds: IntArray, private val icons: IntArray, private val navController: NavController) : RecyclerView.Adapter<ItemViewHolder>() {

    class ItemViewHolder(var gameItemView: ItemView) : RecyclerView.ViewHolder(gameItemView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemViewHolder {
        val v = ItemView(parent.context)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.gameItemView.setIconImage(icons[position])
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("item_id", itemIds[position])
            //navController?.navigate(R.id.action_ItemsFragment_to_ItemDescription, bundle)
            //navController.navigate(R.id.action_itemsFragment_to_item_dialog_fragment, bundle)

            //You can create multiple dialogs on top of each other by tapping very fast
            //or by tapping more than one item at once, call it a feature
            navController.navigate(R.id.item_dialog_fragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return itemIds.size
    }
}