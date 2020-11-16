package com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
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
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.viewPager.adapter = HeroesPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding!!.tabLayout.setupWithViewPager(binding!!.viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}