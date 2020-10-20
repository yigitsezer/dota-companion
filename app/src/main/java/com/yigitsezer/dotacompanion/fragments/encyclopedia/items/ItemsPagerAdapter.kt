package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yigitsezer.dotacompanion.utils.ItemCategory

/**
 * Call order as goes:
 * ItemsFragment -> ItemsPagerAdapter -> ItemListFragment -> ItemListAdapter -> ItemView
 */
class ItemsPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CategoryListFragment.Companion.newInstance(ItemCategory.BASICS)
            1 -> CategoryListFragment.Companion.newInstance(ItemCategory.UPGRADES)
            2 -> CategoryListFragment.Companion.newInstance(ItemCategory.NEUTRAL_ITEMS)
            else -> CategoryListFragment.Companion.newInstance(ItemCategory.BASICS)
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
            0 -> str = "Basics"
            1 -> str = "Upgrades"
            2 -> str = "Neutral Items"
        }
        return str
    }
}