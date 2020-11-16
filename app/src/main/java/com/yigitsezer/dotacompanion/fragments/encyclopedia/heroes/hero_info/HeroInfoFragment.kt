package com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.yigitsezer.dotacompanion.DotaApplication
import com.yigitsezer.dotacompanion.R
import com.yigitsezer.dotacompanion.data.dotabase.Ability
import com.yigitsezer.dotacompanion.data.dotabase.Hero
import com.yigitsezer.dotacompanion.databinding.FragmentHeroInfoBinding
import com.yigitsezer.dotacompanion.databinding.HeroInfoDescriptionViewBinding
import com.yigitsezer.dotacompanion.utils.database.AbilityDao
import com.yigitsezer.dotacompanion.views.RoleProgressView
import java.text.DecimalFormat
import java.util.*

class HeroInfoFragment : Fragment() {
    private var heroId = 0
    private var hero: Hero? = null
    private var binding: FragmentHeroInfoBinding? = null
    private var abilities: List<Ability>? = null

    private lateinit var abilityDao: AbilityDao

    private var heroDescription: HeroInfoDescriptionViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heroId = requireArguments().getInt("hero_id")
        val application = (context as Activity?)!!.application as DotaApplication
        hero = application.getDb()?.heroDao()?.getHeroById(heroId)
        abilities = application.getDb()?.abilityDao()!!.getHeroAbilities(heroId)
        abilityDao = application.getDb()?.abilityDao()!!
        Log.d("HELLOW", hero?.localizedName.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeroInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //add these to an abilitiesAdapter
        //recyclerView.setHasFixedSize(true)
        //recyclerView.layoutManager = GridLayoutManager(this.context, 6, RecyclerView.VERTICAL, false)
        //binding?.heroDescription?.heroAbilitiesRecyclerView?.adapter = HeroAbilitiesAdapter(abilityDao.test(heroId)!!)
        binding?.heroDescription?.heroAbilitiesRecyclerView?.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            it.adapter = HeroAbilitiesAdapter(abilities!!) {
                val bundle = Bundle()
                bundle.putInt("ability_id", it.id)
                findNavController().navigate(R.id.ability_dialog_fragment, bundle)
                Log.d("HELLOW", "this is called")
            }
        }

        //TODO: together with everyone, have the animated portraits in a seperate attribute in json, revisit here when doing that
        //TODO: getImage() returns /panorama/images/heroes/npc_dota_hero_void_spirit_png.png, find another way
        //binding.infoHeroImage.setImageResource(getResources().getIdentifier(hero.getImage(), "drawable", getContext().getPackageName()));
        when (hero?.attrPrimary) {
            "strength" -> binding!!.mainAttribute.setImageDrawable(resources.getDrawable(R.drawable.strength_attribute_symbol))
            "agility" -> binding!!.mainAttribute.setImageDrawable(resources.getDrawable(R.drawable.agility_attribute_symbol))
            "intelligence" -> binding!!.mainAttribute.setImageDrawable(resources.getDrawable(R.drawable.intelligence_attribute_symbol))
        }

        val heroImageName = hero?.fullName + "_png"
        Log.d("HELLOW", "" + resources.getIdentifier(heroImageName, "drawable", context?.packageName))
        binding!!.infoHeroImage.setImageResource(resources.getIdentifier(heroImageName, "drawable", context?.packageName))

        val roles = hero?.roles?.split("\\|".toRegex())?.toTypedArray()
        val roleLevels = hero?.roleLevels?.split("\\|".toRegex())?.toTypedArray()
        binding!!.infoHeroName.text = hero?.localizedName
        //val rolesTextStr = (if (hero?.melee == 1) "MELEE  " else "RANGED  ") + Arrays.toString(roles).replace("[", "").replace("]", "").replace(", ", "  ")
        val rolesTextStr = "<big>" + (if (hero?.melee == 1) "MELEE" else "RANGED") + "</big><br>" + Arrays.toString(roles).replace("[", "").replace("]", "").replace(", ", "<br>")
        binding!!.infoHeroRoles.text = HtmlCompat.fromHtml(rolesTextStr, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding?.heroDescription?.hypeText?.text = hero?.hype
        setStatTexts(1)

        //Removed the seekbar for the time being
        binding?.heroDescription?.seekBar?.max = 30
        binding?.heroDescription?.seekBar?.progress = 1
        binding?.heroDescription?.seekBar?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val lvltext = "Level: $progress"
                //setStatTexts(progress);
                binding?.heroDescription?.levelSeekText?.text = lvltext
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        val barViews: MutableList<RoleProgressView> = ArrayList()
        binding?.heroDescription?.roleBar1?.let { barViews.add(it) } //carry
        binding?.heroDescription?.roleBar2?.let { barViews.add(it) } //support
        binding?.heroDescription?.roleBar3?.let { barViews.add(it) } //nuker
        binding?.heroDescription?.roleBar4?.let { barViews.add(it) } //disabler
        binding?.heroDescription?.roleBar5?.let { barViews.add(it) } //jungler
        binding?.heroDescription?.roleBar6?.let { barViews.add(it) } //durable
        binding?.heroDescription?.roleBar7?.let { barViews.add(it) } //escape
        binding?.heroDescription?.roleBar8?.let { barViews.add(it) } //pusher
        binding?.heroDescription?.roleBar9?.let { barViews.add(it) } //initiator
        var roleIndex = 0
        if (roles != null) {
            for (i in roles.indices) {
                roleIndex = getRoleIndex(roles[i])
                barViews[roleIndex].setLevel(roleLevels?.get(i)!!.toInt())

            }
        }

        val adRequest = AdRequest.Builder().build()
        binding!!.adView.loadAd(adRequest)
        //binding!!.heroInfoDescriptionScroll.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding!!.adView.destroy()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getRoleIndex(s: String): Int {
        when(s) {
            "Carry" -> return 0
            "Support" -> return 1
            "Nuker" -> return 2
            "Disabler" -> return 3
            "Jungler" -> return 4
            "Durable" -> return 5
            "Escape" -> return 6
            "Pusher" -> return 7
            "Initiator" -> return 8
            else -> return 0
        }
    }

    private fun setStatTexts(level: Int) {
        var primaryAttrBase = 0
        when (hero?.attrPrimary) {
            "strength" -> primaryAttrBase = hero?.attrStrengthBase!!
            "agility" -> primaryAttrBase = hero?.attrAgilityBase!!
            "intelligence" -> primaryAttrBase = hero?.attrIntelligenceBase!!
        }
        val strText = hero?.attrStrengthBase.toString() + " + " + df.format(hero?.attrStrengthGain)
        val agiText = hero?.attrAgilityBase.toString() + " + " + df.format(hero?.attrAgilityGain)
        val intText = hero?.attrIntelligenceBase.toString() + " + " + df.format(hero?.attrIntelligenceGain)
        val attackDistribution = (hero?.attackDamageMin?.plus(primaryAttrBase)).toString() + " - " + (hero?.attackDamageMax?.plus(primaryAttrBase))
        //TODO: this is actually "attack time", which is calculated differently
        val attackRate = df.format(hero?.attackRate) //IDK
        val attackRange = hero?.attackRange.toString() //30 BY TALENT
        val armorText = df.format(hero?.attrAgilityBase?.times(0.17)?.let { hero?.baseArmor?.plus(it) }) //TALENT AND LEVEL
        val magicResistance = hero?.magicResistance.toString() + "%" //30 BY TALENT
        val movementSpeed = hero?.baseMovement.toString() //30 BY TALENT
        val turnRate = df.format(hero?.turnRate) //NOT AFFECTED
        val visionText = hero?.visionDay.toString() + " / " + hero?.visionNight //NOT AFFECTED, MAYBE BY TALENT 30
        val health = df1.format(200 + (hero?.attrStrengthBase?.times(20.toLong()) ?: 0))
        val healthRegen = "+" + df.format((hero?.baseHealthRegen ?: 0).toFloat().plus((hero?.attrStrengthBase ?: 0).times(0.1)))
        val mana = df1.format(75 + (hero?.attrIntelligenceBase?.times(12.toLong()) ?: 0))
        val manaRegen = "+" + df.format((hero?.baseManaRegen ?: 0).toFloat().plus((hero?.attrIntelligenceBase ?: 0).times(0.05)))
        binding?.heroDescription?.strPerLevelText?.text = strText
        binding?.heroDescription?.agiPerLevelText?.text = agiText
        binding?.heroDescription?.intPerLevelText?.text = intText
        binding?.heroDescription?.attackDistribution?.text = attackDistribution
        binding?.heroDescription?.attackRateText?.text = attackRate
        binding?.heroDescription?.attackRangeText?.text = attackRange
        binding?.heroDescription?.armorText?.text = armorText
        binding?.heroDescription?.magicResistanceText?.text = magicResistance
        binding?.heroDescription?.movementSpeedText?.text = movementSpeed
        binding?.heroDescription?.turnRateText?.text = turnRate
        binding?.heroDescription?.visionText?.text = visionText
        binding?.heroDescription?.healthText?.text = health
        binding?.heroDescription?.healthRegenText?.text = healthRegen
        binding?.heroDescription?.manaText?.text = mana
        binding?.heroDescription?.manaRegenText?.text = manaRegen
    }

    companion object {
        private val df = DecimalFormat("0.0")
        private val df1 = DecimalFormat("0")
    }
}
