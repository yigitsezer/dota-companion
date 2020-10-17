package com.example.dotacompanion

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dotacompanion.databinding.ActivityMainBinding
import com.example.dotacompanion.utils.database.DotaDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView


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
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
        bottomNavigationView.setOnNavigationItemSelectedListener {
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

        val handler = Handler()
        val delay = 1000 //milliseconds
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        navController = null
    }
}