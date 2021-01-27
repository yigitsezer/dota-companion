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

            /*
            //DEBUG
            var names = arrayOf("item_tpscroll", "item_clarity", "item_faerie_fire", "item_smoke_of_deceit", "item_ward_observer", "item_ward_sentry", "item_enchanted_mango", "item_flask", "item_tango", "item_tome_of_knowledge", "item_dust", "item_bottle", "item_aghanims_shard", "item_branches", "item_gauntlets", "item_slippers", "item_mantle", "item_circlet", "item_belt_of_strength", "item_boots_of_elves", "item_robe", "item_crown", "item_ogre_axe", "item_blade_of_alacrity", "item_staff_of_wizardry", "item_quelling_blade", "item_ring_of_protection", "item_infused_raindrop", "item_orb_of_venom", "item_blight_stone", "item_blades_of_attack", "item_gloves", "item_chainmail", "item_quarterstaff", "item_helm_of_iron_will", "item_broadsword", "item_blitz_knuckles", "item_javelin", "item_claymore", "item_mithril_hammer", "item_ring_of_regen", "item_sobi_mask", "item_magic_stick", "item_fluffy_hat", "item_wind_lace", "item_cloak", "item_boots", "item_gem", "item_lifesteal", "item_voodoo_mask", "item_shadow_amulet", "item_ghost", "item_blink", "item_magic_wand", "item_null_talisman", "item_wraith_band", "item_bracer", "item_soul_ring", "item_orb_of_corrosion", "item_falcon_blade", "item_power_treads", "item_phase_boots", "item_oblivion_staff", "item_pers", "item_mask_of_madness", "item_hand_of_midas", "item_helm_of_the_dominator", "item_travel_boots", "item_moon_shard", "item_buckler", "item_ring_of_basilius", "item_headdress", "item_urn_of_shadows", "item_tranquil_boots", "item_medallion_of_courage", "item_arcane_boots", "item_ancient_janggo", "item_mekansm", "item_holy_locket", "item_vladmir", "item_spirit_vessel", "item_pipe", "item_guardian_greaves", "item_veil_of_discord", "item_glimmer_cape", "item_necronomicon", "item_force_staff", "item_aether_lens", "item_witch_blade", "item_cyclone", "item_rod_of_atos", "item_dagon", "item_orchid", "item_solar_crest", "item_ultimate_scepter", "item_refresher", "item_octarine_core", "item_sheepstick", "item_gungir", "item_wind_waker", "item_hood_of_defiance", "item_vanguard", "item_blade_mail", "item_aeon_disk", "item_soul_booster", "item_eternal_shroud", "item_crimson_guard", "item_lotus_orb", "item_black_king_bar", "item_hurricane_pike", "item_manta", "item_sphere", "item_shivas_guard", "item_heart", "item_assault", "item_bloodstone", "item_lesser_crit", "item_meteor_hammer", "item_armlet", "item_basher", "item_invis_sword", "item_desolator", "item_bfury", "item_ethereal_blade", "item_nullifier", "item_monkey_king_bar", "item_butterfly", "item_radiance", "item_greater_crit", "item_silver_edge", "item_rapier", "item_bloodthorn", "item_abyssal_blade", "item_dragon_lance", "item_sange", "item_yasha", "item_kaya", "item_echo_sabre", "item_maelstrom", "item_diffusal_blade", "item_mage_slayer", "item_heavens_halberd", "item_kaya_and_sange", "item_sange_and_yasha", "item_yasha_and_kaya", "item_satanic", "item_skadi", "item_mjollnir", "item_overwhelming_blink", "item_swift_blink", "item_arcane_blink", "item_ring_of_health", "item_void_stone", "item_energy_booster", "item_vitality_booster", "item_point_booster", "item_platemail", "item_talisman_of_evasion", "item_hyperstone", "item_ultimate_orb", "item_demon_edge", "item_mystic_staff", "item_reaver", "item_eagle", "item_relic")
            var ids = ArrayList<Int>()
            for (i in names)
                ids.add(db.abilityDao().test(i))
            Log.d("HELLOW", ids.toString())

            var ids = intArrayOf(287, 304, 305, 349, 354, 355, 356, 375, 565, 577, 589,
                    212, 288, 290, 306, 331, 334, 357, 358, 359, 680, 686,
                    289, 309, 326, 361, 376, 378, 381, 573, 574, 675, 676,
                    300, 311, 335, 336, 362, 363, 377, 379, 571, 585, 638,
                    291, 292, 294, 301, 366, 367, 370, 371, 372, 374, 677, 678)
            var names = ArrayList<String>()
            for (i in ids)
                names.add(db.abilityDao().test2(i))
            Log.d("HELLOW", names.toString())
             */

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