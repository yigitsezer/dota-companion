package com.yigitsezer.dotacompanion

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.android.gms.ads.MobileAds
import com.yigitsezer.dotacompanion.databinding.ActivityMainBinding
import com.yigitsezer.dotacompanion.utils.database.DotaDatabase


class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var navController: NavController? = null
    var application: DotaApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        requestWindowFeature(Window.FEATURE_NO_TITLE) //will hide the title
        supportActionBar!!.hide() //hide the title bar
        setContentView(view)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //do this so it cant load the same fragment again, but it can't go back to main bottom fragment like hero_info->encyclopedia
        /*
        bottomNavigationView.setOnNavigationItemReselectedListener { BottomNavigationView.OnNavigationItemReselectedListener {
            /*
            val curDest = navController.currentDestination?.label
            if (curDest != "fragment_about" && curDest != "fragment_encyclopedia" || curDest != "fragment_primary_profile") {

            }
             */
        } }
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
         */
        //I think this works for now

        //TODO: With no content on the other main bottom fragments I dont see a point in
        // keeping the bottom navigation bar for now, when I actually make them and think of a better UX
        // I will use the bottom navigation, or maybe some different UI design either way im keeping these here

        binding?.bottomNavigation?.visibility = View.GONE

        /*
        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.encyclopediaFragment -> {
                    //navController?.navigate(R.id.encyclopediaFragment)
                    navController?.popBackStack(R.id.encyclopediaFragment, false)
                    true
                }
                R.id.profileNavigation -> {
                    navController?.navigate(R.id.profileNavigation)
                    true
                }
                R.id.aboutFragment -> {
                    navController?.navigate(R.id.aboutFragment)
                    true
                }
                else -> {
                    navController?.navigate(R.id.encyclopediaFragment)
                    true
                }
            }
        }
        */
        if (application == null) {
            val rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    Log.d("HELLOW", "onCreate: first time")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    // do something every time database is open
                }
            }

            val db = Room.databaseBuilder(applicationContext, DotaDatabase::class.java, "Sample4.db")
                    .createFromAsset("dotabase4.db")
                    .allowMainThreadQueries()
                    .addCallback(rdc)
                    .build()

            application = getApplication() as DotaApplication
            application?.setDb(db)
        }

        //val handler = Handler()
        //val delay = 1000 //milliseconds
        MobileAds.initialize(this) {}

    }

    override fun onBackPressed() {
        if (this.isTaskRoot && supportFragmentManager.backStackEntryCount > 1)
            finishAfterTransition()
        else
            super.onBackPressed()
    }

    //TODO: This is bullshit, I made this because at this point the only destination back button
    // can take you is encyclopedia fragment, when profile and about (or whatever it is) fragments are done
    // create different navgraphs with different backstacks for each of them, or however it is done at that time
    // so you can control the bottom navigation highlights easily
    /*
    override fun onBackPressed() {
        super.onBackPressed()
        binding?.bottomNavigation?.menu?.getItem(2)?.isChecked = false
        binding?.bottomNavigation?.menu?.getItem(1)?.isChecked = false
        binding?.bottomNavigation?.menu?.getItem(0)?.isChecked = true
    }
     */

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        navController = null
    }
}