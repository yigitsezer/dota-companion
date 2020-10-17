package com.example.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dotacompanion.DotaApplication
import com.example.dotacompanion.R
import com.example.dotacompanion.data.dotabase.Hero
import com.example.dotacompanion.databinding.FragmentHeroInfoBinding
import com.example.dotacompanion.databinding.HeroInfoDescriptionViewBinding
import com.example.dotacompanion.views.RoleProgressView
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class HeroInfoFragment : Fragment() {
    private var heroId = 0
    private var hero: Hero? = null
    private var application: DotaApplication? = null
    private var binding: FragmentHeroInfoBinding? = null

    private var heroDescription: HeroInfoDescriptionViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heroId = requireArguments().getInt("hero_id")
        application = (context as Activity?)!!.application as DotaApplication
        hero = application?.getDb()?.heroDao()?.getHeroById(heroId)
        Log.d("HELLOW", hero?.localizedName.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeroInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        val rolesTextStr = (if (hero?.melee == 1) "MELEE  " else "RANGED  ") + Arrays.toString(roles).replace("[", "").replace("]", "").replace(", ", "  ")
        binding!!.infoHeroRoles.text = rolesTextStr
        binding?.heroDescription?.hypeText?.text = hero?.hype
        setStatTexts(1)
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
        //binding!!.heroInfoDescriptionScroll.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
        val attackRate = df.format(hero?.attackRate)
        val attackRange = hero?.attackRange.toString()
        val armorText = df.format(hero?.attrAgilityBase?.times(0.17)?.let { hero?.baseArmor?.plus(it) })
        val magicResistance = hero?.magicResistance.toString() + "%"
        val movementSpeed = hero?.baseMovement.toString()
        val turnRate = df.format(hero?.turnRate)
        val visionText = hero?.visionDay.toString() + " / " + hero?.visionNight
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
