package com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.R
import com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes.hero_info.HeroAbilitiesAdapter.HeroAbilityViewHolder
import com.yigitsezer.dotacompanion.data.dotabase.Ability
import com.yigitsezer.dotacompanion.views.AbilityView

class HeroAbilitiesAdapter(private var abilities: List<Ability>) : RecyclerView.Adapter<HeroAbilityViewHolder>() {
    class HeroAbilityViewHolder(var abilityView: AbilityView) : RecyclerView.ViewHolder(abilityView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HeroAbilityViewHolder {
        val v = AbilityView(parent.context)
        v.layoutParams = LinearLayout.LayoutParams(parent.measuredWidth / itemCount, ViewGroup.LayoutParams.WRAP_CONTENT)
        return HeroAbilityViewHolder(v)
    }

    override fun onBindViewHolder(holder: HeroAbilityViewHolder, position: Int) {
        val context = holder.abilityView.context
        //what the fuck
        holder.abilityView.abilityImage.setImageResource(context.resources.getIdentifier(abilities[position].icon?.substringAfterLast("/")?.substringBefore(".png"), "drawable", context?.packageName))

        //FIXME: only time I have seen this was on morphling where it's last ability got past the init check
        // and it was showing a blank image since the so called drawable was not available and shouldnt be anyway
        // either remove abilities that have no icons in R.drawables or find a new init check because:
        // Cannot call this method while RecyclerView is computing a layout
        //if (holder.abilityView.abilityImage.drawable == null) { notifyItemRemoved(position) }
    }

    override fun getItemCount(): Int {
        return abilities.size
    }

    init {
        var a = ArrayList<Ability>()
        for (i in abilities) {
            if (i.abilitySlot != null && i.aghanimGrants == 0 && i.abilitySpecial!! != "[]" && !i.behavior?.contains("hidden")!!) {
                a.add(i)
            }
        }
        Log.d("HELLOW", "initcalled")
        //IM SO FUCKING SMART
        abilities = a.sortedBy { it.abilitySlot }
    }
}