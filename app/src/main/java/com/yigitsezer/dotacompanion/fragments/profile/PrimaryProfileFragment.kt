package com.yigitsezer.dotacompanion.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.yigitsezer.dotacompanion.R
import com.yigitsezer.dotacompanion.databinding.FragmentPrimaryProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PrimaryProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//TODO: Rework the whole profile thing
class PrimaryProfileFragment : Fragment() {
    private var viewModel: SharedProfileViewModel? = null
    private var binding: FragmentPrimaryProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPrimaryProfileBinding.inflate(inflater, container, false)
        val navController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        val profileNav = navGraph.findNode(R.id.profileNavigation) as NavGraph?
        viewModel = ViewModelProvider(requireActivity()).get(SharedProfileViewModel::class.java)
        /*
        binding?.getMatchesButton?.setOnClickListener {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.opendota.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val jsonPlaceHolderApi = retrofit.create(DotaApi::class.java)
            val call = jsonPlaceHolderApi.getPlayerData("128092127")
            call!!.enqueue(object : Callback<Player?> {
                override fun onResponse(call: Call<Player?>, response: Response<Player?>) {
                    Log.d("HELLOW", "REQUEST: " + call.request().url)
                    if (!response.isSuccessful) {
                        Log.d("HELLOW", "Code: " + response.code())
                        return
                    }
                    val post = response.body()
                    //Log.d("HELLOW", "INFO: " + post.getProfile().avatarfull)
                }

                override fun onFailure(call: Call<Player?>, t: Throwable) {
                    //Log.d("HELLOW", t.toString())
                }
            })

            //TEST
            //navController.navigate(R.id.action_profileFragment_to_fetchedProfileFragment);
            profileNav!!.startDestination = R.id.fetchedProfileFragment
            navController.graph = navGraph
            navController.navigate(R.id.profileNavigation)
            viewModel?.setText(binding?.steamIdField?.text as CharSequence)
        }
        */
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}