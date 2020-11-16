package com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.R
import com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes.hero_info.HeroAbilitiesAdapter.HeroAbilityViewHolder
import com.yigitsezer.dotacompanion.data.dotabase.Ability
import com.yigitsezer.dotacompanion.views.AbilityView

class HeroAbilitiesAdapter(var abilities: List<Ability>, val onAbilityClicked : (Ability) -> Unit) : RecyclerView.Adapter<HeroAbilityViewHolder>() {
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
        holder.abilityView.setOnClickListener {
            onAbilityClicked(abilities[position])
        }

        //FIXME: only time I have seen this was on morphling where it's last ability got past the init check
        // and it was showing a blank image since the so called drawable was not available and shouldnt be anyway
        // either remove abilities that have no icons in R.drawables or find a new init check because:
        // Cannot call this method while RecyclerView is computing a layout
        //if (holder.abilityView.abilityImage.drawable == null) { notifyItemRemoved(position) }
    }

    override fun getItemCount(): Int {
        return abilities.size
    }
}