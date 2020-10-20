package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.databinding.FragmentItemListBinding
import com.yigitsezer.dotacompanion.utils.ItemCategory

class CategoryListFragment : Fragment() {
    var binding: FragmentItemListBinding? = null
    private var itemCategory
            : ItemCategory? = null

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemCategory = arguments?.getSerializable("category") as ItemCategory?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        binding!!.categoriesRecyclerView.setHasFixedSize(true)
        binding!!.categoriesRecyclerView.layoutManager = GridLayoutManager(this.context, 1, RecyclerView.VERTICAL, false)
        navController = this.findNavController()
        binding!!.categoriesRecyclerView.adapter = CategoryListAdapter(context, itemCategory, navController)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(itemCategory: ItemCategory?): CategoryListFragment {
            val f = CategoryListFragment()
            val args = Bundle()
            args.putSerializable("category", itemCategory)
            f.arguments = args
            return f
        }
    }
}