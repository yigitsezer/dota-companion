package com.example.dotacompanion.fragments.encyclopedia.heroes

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dotacompanion.utils.HeroAttribute

/**
 * Call order as goes:
 * HeroesFragment -> HeroesPagerAdapter -> HeroListFragment -> HeroListAdapter -> HeroView
 */
class HeroesPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HeroListFragment.Companion.newInstance(HeroAttribute.STRENGTH)
            1 -> HeroListFragment.Companion.newInstance(HeroAttribute.AGILITY)
            2 -> HeroListFragment.Companion.newInstance(HeroAttribute.INTELLIGENCE)
            else -> HeroListFragment.Companion.newInstance(HeroAttribute.INTELLIGENCE)
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var str: CharSequence? = null
        when (position) {
            0 -> str = "Strength"
            1 -> str = "Agility"
            2 -> str = "Intelligence"
        }
        return str
    }
}