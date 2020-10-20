package com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.yigitsezer.dotacompanion.DotaApplication
import com.yigitsezer.dotacompanion.databinding.FragmentHeroesBinding

class HeroesFragment : Fragment() {
    var binding: FragmentHeroesBinding? = null
    var application: DotaApplication? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        binding!!.viewPager.adapter = HeroesPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding!!.tabLayout.setupWithViewPager(binding!!.viewPager)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}