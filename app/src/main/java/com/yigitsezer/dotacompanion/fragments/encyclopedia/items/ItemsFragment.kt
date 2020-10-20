package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yigitsezer.dotacompanion.DotaApplication
import com.yigitsezer.dotacompanion.databinding.FragmentItemsBinding
import com.yigitsezer.dotacompanion.fragments.encyclopedia.items.viewmodel.ItemSharedViewModel
import com.yigitsezer.dotacompanion.fragments.encyclopedia.items.viewmodel.MyViewModelFactory


class ItemsFragment : Fragment() {
    var binding: FragmentItemsBinding? = null
    var viewModel: ItemSharedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentItemsBinding.inflate(inflater, container, false)
        binding!!.viewPager.adapter = ItemsPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding!!.tabLayout.setupWithViewPager(binding!!.viewPager)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = (activity?.application as DotaApplication).getDb()
        viewModel = ViewModelProvider(this, MyViewModelFactory(db!!)).get(ItemSharedViewModel::class.java)
        viewModel!!.getItem(174)
        Log.d("HELLOW", "asdasdasd")
        viewModel!!.itemLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("HELLOW", ""+ it.localized_name)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}