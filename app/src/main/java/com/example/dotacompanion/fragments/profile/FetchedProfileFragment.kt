package com.example.dotacompanion.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dotacompanion.databinding.FragmentFetchedProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FetchedProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FetchedProfileFragment : Fragment() {
    private var viewModel: SharedProfileViewModel? = null
    private var binding: FragmentFetchedProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFetchedProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedProfileViewModel::class.java)
        binding!!.playerIdText.text = viewModel?.getText().toString()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}