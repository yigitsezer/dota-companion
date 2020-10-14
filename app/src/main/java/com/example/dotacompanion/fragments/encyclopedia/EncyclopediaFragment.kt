package com.example.dotacompanion.fragments.encyclopedia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.dotacompanion.R
import com.example.dotacompanion.databinding.FragmentEncyclopediaBinding

class EncyclopediaFragment : Fragment() {
    var binding: FragmentEncyclopediaBinding? = null
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fuck these
        //navController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
        //navController = ((context as Activity?)!!.application as DotaApplication).getNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentEncyclopediaBinding.inflate(inflater, container, false)
        binding!!.heroesCard.setOnClickListener {
            navController?.navigate(R.id.heroesFragment)
        }
        binding!!.itemsCard.setOnClickListener {
            navController?.navigate(R.id.itemsFragment)
        }
        binding!!.updatesCard.setOnClickListener {
            navController?.navigate(R.id.mainFragment)
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        navController = null
    }
}