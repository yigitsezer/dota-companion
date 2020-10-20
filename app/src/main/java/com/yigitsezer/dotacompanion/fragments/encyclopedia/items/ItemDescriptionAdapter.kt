package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.data.dotabase.Item
import com.yigitsezer.dotacompanion.views.ItemDescriptionActiveView
import com.yigitsezer.dotacompanion.views.ItemDescriptionPassiveView
import java.util.regex.Pattern


class ItemDescriptionAdapter(private val context: Context?, private val item: Item?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //private val navController: NavController?
    private val TYPE_ACTIVE = 1
    private val TYPE_PASSIVE = 2
    private var dTitleTypes: ArrayList<String> = ArrayList() //Passive, Active, Use, Upgrade
    private var dTitleName: ArrayList<String> = ArrayList()
    private var dDescriptions: ArrayList<String> = ArrayList()


    override fun getItemViewType(position: Int): Int {
        return if (dTitleTypes[position] == "Active") {
            TYPE_ACTIVE;
        } else {
            TYPE_PASSIVE;
        }
    }

    internal class ActiveViewHolder(var descView: ItemDescriptionActiveView) : RecyclerView.ViewHolder(descView)

    internal class PassiveViewHolder(var descView: ItemDescriptionPassiveView) : RecyclerView.ViewHolder(descView)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ACTIVE) {
            val view = ItemDescriptionActiveView(viewGroup.context)
            return ActiveViewHolder(view)
        } else {
            val view = ItemDescriptionPassiveView(viewGroup.context)
            return PassiveViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ACTIVE) {
            val v = (viewHolder as ActiveViewHolder)
            v.descView.setActiveText(dTitleTypes[position] + ": " + dTitleName[position])
            v.descView.setDescriptionText(dDescriptions[position])
            item?.cooldown?.let { v.descView.setCooldownText(it) }
            item?.mana_cost?.let { v.descView.setManacostText(it) }
        } else {
            val v = (viewHolder as PassiveViewHolder)
            v.descView.setActiveText(dTitleTypes[position] + ": " + dTitleName[position])
            v.descView.setDescriptionText(dDescriptions[position])
        }
    }

    init {
        //navController = ((this.context as Activity?)!!.application as DotaApplication).getNavController()
        val p = Pattern.compile("# (.*): (.*)([^#]*)")
        val m = p.matcher(item?.description?.replace(Regex("\\*\\*(.*?)\\*\\*"), "<b>$1</b>"))
        while (m.find()) {
            dTitleTypes.add(m.group(1))
            dTitleName.add(m.group(2))
            Log.d("HELLOW", ""+m.group(3))
            Log.d("HELLOW", ""+item?.description)
            dDescriptions.add(m.group(3).trim())
        }
    }

    override fun getItemCount(): Int {
        return dTitleTypes.size
    }
}