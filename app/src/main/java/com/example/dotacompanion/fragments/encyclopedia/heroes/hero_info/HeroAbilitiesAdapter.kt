package com.example.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dotacompanion.fragments.encyclopedia.heroes.hero_info.HeroAbilitiesAdapter.HeroAbilitiesHolder
import com.example.dotacompanion.data.dotabase.Ability
import com.example.dotacompanion.views.HeroView

class HeroAbilitiesAdapter(private val context: Context, private val abilities: List<Ability>) : RecyclerView.Adapter<HeroAbilitiesHolder>() {
    class HeroAbilitiesHolder(var heroView: HeroView) : RecyclerView.ViewHolder(heroView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HeroAbilitiesHolder {
        val v = HeroView(parent.context)
        return HeroAbilitiesHolder(v)
    }

    override fun onBindViewHolder(holder: HeroAbilitiesHolder, position: Int) {}
    override fun getItemCount(): Int {
        return abilities.size
    }
}