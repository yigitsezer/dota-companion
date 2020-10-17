package com.example.dotacompanion.fragments.encyclopedia.heroes

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dotacompanion.DotaApplication
import com.example.dotacompanion.R
import com.example.dotacompanion.fragments.encyclopedia.heroes.HeroListAdapter.HeroViewHolder
import com.example.dotacompanion.utils.Constants
import com.example.dotacompanion.utils.HeroAttribute
import com.example.dotacompanion.views.HeroView

class HeroListAdapter(private val context: Context?, private val listAttribute: HeroAttribute?, private val navController: NavController) : RecyclerView.Adapter<HeroViewHolder>() {
    private lateinit var heroNames: Array<String>
    private lateinit var heroIds: IntArray
    private lateinit var heroImages: IntArray

    class HeroViewHolder(var heroView: HeroView) : RecyclerView.ViewHolder(heroView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HeroViewHolder {
        val v = HeroView(parent.context)
        return HeroViewHolder(v)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.heroView.setHeroId(heroIds[position])
        holder.heroView.setHeroName(heroNames[position])
        holder.heroView.setHeroImage(heroImages[position])
        holder.heroView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("hero_id", heroIds[position])
            //navController?.navigate(R.id.action_heroesFragment_to_heroInfoFragment, bundle)
            navController.navigate(R.id.action_heroesFragment_to_heroInfoFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return heroIds.size
    }

    init {
        when (listAttribute) {
            HeroAttribute.STRENGTH -> {
                heroNames = Constants.STRENGTH_HEROES
                heroIds = Constants.STRENGTH_HEROES_ID
                heroImages = Constants.STRENGTH_HEROES_IMAGE
            }
            HeroAttribute.AGILITY -> {
                heroNames = Constants.AGILITY_HEROES
                heroIds = Constants.AGILITY_HEROES_ID
                heroImages = Constants.AGILITY_HEROES_IMAGE
            }
            HeroAttribute.INTELLIGENCE -> {
                heroNames = Constants.INTELLIGENCE_HEROES
                heroIds = Constants.INTELLIGENCE_HEROES_ID
                heroImages = Constants.INTELLIGENCE_HEROES_IMAGE
            }
        }
    }
}