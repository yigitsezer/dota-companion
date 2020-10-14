package com.example.dotacompanion.fragments.encyclopedia.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotacompanion.databinding.FragmentHeroListBinding
import com.example.dotacompanion.utils.HeroAttribute

/**
 * Dont confuse with HeroesFragment, use this fragment
 * to create other fragments for each attribute type (str, agi, int)
 */
class HeroListFragment : Fragment() {
    var binding: FragmentHeroListBinding? = null
    private var listAttribute //str, agi, int
            : HeroAttribute? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAttribute = arguments?.getSerializable("attribute") as HeroAttribute?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeroListBinding.inflate(inflater, container, false)
        binding!!.recyclerView.setHasFixedSize(true)
        binding!!.recyclerView.layoutManager = GridLayoutManager(this.context, 3, RecyclerView.VERTICAL, false)
        binding!!.recyclerView.adapter = HeroListAdapter(context, listAttribute, this.findNavController())
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(attribute: HeroAttribute?): HeroListFragment {
            val f = HeroListFragment()
            val args = Bundle()
            args.putSerializable("attribute", attribute)
            f.arguments = args
            return f
        }
    }
}