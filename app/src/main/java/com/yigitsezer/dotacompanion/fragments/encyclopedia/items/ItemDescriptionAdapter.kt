package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.data.dotabase.Item
import com.yigitsezer.dotacompanion.views.ItemDescriptionActiveView
import com.yigitsezer.dotacompanion.views.ItemDescriptionPassiveView
import com.yigitsezer.dotacompanion.views.ItemDescriptionSingleView
import java.util.regex.Pattern


class ItemDescriptionAdapter(private val context: Context?, private val item: Item?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //private val navController: NavController?
    private val TYPE_ACTIVE = 1
    private val TYPE_PASSIVE = 2
    private val TYPE_SINGLE = 3
    private var dTitleTypes: ArrayList<String> = ArrayList() //Passive, Active, Use, Upgrade
    private var dTitleName: ArrayList<String?> = ArrayList()
    private var dDescriptions: ArrayList<String?> = ArrayList()


    override fun getItemViewType(position: Int): Int {
        return if (dTitleTypes[position].contains(Regex("^Active"))) {
            TYPE_ACTIVE
        } else if (dTitleTypes[position].contains(Regex("^Passive"))) {
            TYPE_PASSIVE
        } else
            TYPE_SINGLE
    }

    internal class ActiveViewHolder(var descView: ItemDescriptionActiveView) : RecyclerView.ViewHolder(descView)

    internal class PassiveViewHolder(var descView: ItemDescriptionPassiveView) : RecyclerView.ViewHolder(descView)

    internal class SingleViewHolder(var descView: ItemDescriptionSingleView) : RecyclerView.ViewHolder(descView)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ACTIVE) {
            val view = ItemDescriptionActiveView(viewGroup.context)
            return ActiveViewHolder(view)
        } else if (viewType == TYPE_PASSIVE){
            val view = ItemDescriptionPassiveView(viewGroup.context)
            return PassiveViewHolder(view)
        } else {
            val view = ItemDescriptionSingleView(viewGroup.context)
            return SingleViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ACTIVE) {
            val v = (viewHolder as ActiveViewHolder)
            v.descView.setActiveText(dTitleTypes[position])
            Log.d("HELLOW", dTitleTypes[position])
            dDescriptions[position]?.let { v.descView.setDescriptionText(it) }

            if (item?.cooldown != null && item.cooldown != "0")
                v.descView.setCooldownText(item.cooldown!!.replace(" ", " / "))
            else
                v.descView.removeCooldown()

            if (item?.mana_cost != null && item.mana_cost != "0")
                v.descView.setManacostText(item.mana_cost!!.replace(" ", " / "))
            else
                v.descView.removeManacost()

        } else if (getItemViewType(position) == TYPE_PASSIVE){
            val v = (viewHolder as PassiveViewHolder)
            v.descView.setActiveText(dTitleTypes[position])
            dDescriptions[position]?.let { v.descView.setDescriptionText(it) }
        } else {
            val v = (viewHolder as SingleViewHolder)
            dDescriptions[position]?.let { v.descView.setDescriptionText(it) }
        }
    }

    init {
        //item description is never null so dont worry
        val p = Pattern.compile("# (.*)(: (.*))?([^#]*)")
        val coolString = item?.description?.replace(Regex("\\*\\*(.*?)\\*\\*"), "<b>$1</b>")
        val m = p.matcher(coolString)
        // FIXME: there should be a better way to write this
        if (m.find()) {
            dTitleTypes.add(m.group(1))
            dTitleName.add(m.group(3))
            dDescriptions.add(m.group(4).trim())
            while (m.find()) {
                dTitleTypes.add(m.group(1))
                dTitleName.add(m.group(3))
                dDescriptions.add(m.group(4).trim())
            }
        } else if (coolString!!.isNotEmpty()) {
            dTitleTypes.add("Single")
            dTitleName.add("")
            dDescriptions.add(coolString)
        }
        Log.d("HELLOW", dTitleTypes.toString())

        //This is used to fix necronomicon description, I couldnt think of a smart regex
        //what the fuck
        if (item?.description?.contains("# Warrior")!!) {
            dDescriptions[0] = coolString!!.replace(Regex("# Active.*"), "")
                    .replace(Regex("# (.*)"), "<b>$1</b>")
                    .replace(Regex("[\\r\\n]{2}"), "\n")
                    .trim()
        }
    }

    override fun getItemCount(): Int {
        return dTitleTypes.size
    }
}