package com.example.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import com.example.dotacompanion.DotaApplication
import com.example.dotacompanion.R
import com.example.dotacompanion.data.dotabase.Hero
import com.example.dotacompanion.databinding.FragmentHeroInfoBinding
import com.example.dotacompanion.views.RoleProgressView
import java.text.DecimalFormat
import java.util.*

class HeroInfoFragment : Fragment() {
    private var heroId = 0
    private var hero: Hero? = null
    private var application: DotaApplication? = null
    private var binding: FragmentHeroInfoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heroId = requireArguments().getInt("hero_id")
        application = (context as Activity?)!!.application as DotaApplication
        hero = application?.getDb()?.heroDao()?.getHeroById(heroId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeroInfoBinding.inflate(inflater, container, false)

        //TODO: together with everyone, have the animated portraits in a seperate attribute in json, revisit here when doing that
        //TODO: getImage() returns /panorama/images/heroes/npc_dota_hero_void_spirit_png.png, find another way
        //binding.infoHeroImage.setImageResource(getResources().getIdentifier(hero.getImage(), "drawable", getContext().getPackageName()));
        when (hero?.attrPrimary) {
            "strength" -> binding!!.mainAttribute.setImageDrawable(resources.getDrawable(R.drawable.strength_attribute_symbol))
            "agility" -> binding!!.mainAttribute.setImageDrawable(resources.getDrawable(R.drawable.agility_attribute_symbol))
            "intelligence" -> binding!!.mainAttribute.setImageDrawable(resources.getDrawable(R.drawable.intelligence_attribute_symbol))
        }

        var heroImageName = hero?.fullName + "_png"
        Log.d("HELLOW", "" + resources.getIdentifier(heroImageName, "drawable", context?.packageName))
        binding!!.infoHeroImage.setImageResource(resources.getIdentifier(heroImageName, "drawable", context?.packageName))

        val roles = hero?.roles?.split("\\|".toRegex())?.toTypedArray()
        val roleLevels = hero?.roleLevels?.split("\\|".toRegex())?.toTypedArray()
        binding!!.infoHeroName.text = hero?.localizedName
        val rolesTextStr = (if (hero?.melee == 1) "MELEE  " else "RANGED  ") + Arrays.toString(roles).replace("[", "").replace("]", "").replace(", ", "  ")
        binding!!.infoHeroRoles.text = rolesTextStr
        binding!!.hypeText.text = hero?.hype
        setStatTexts(1)
        binding!!.seekBar.max = 30
        binding!!.seekBar.progress = 1
        binding!!.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val lvltext = "Level: $progress"
                //setStatTexts(progress);
                binding!!.levelSeekText.text = lvltext
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        val barViews: MutableList<RoleProgressView> = ArrayList()
        barViews.add(binding!!.roleBar1) //carry
        barViews.add(binding!!.roleBar2) //support
        barViews.add(binding!!.roleBar3) //nuker
        barViews.add(binding!!.roleBar4) //disabler
        barViews.add(binding!!.roleBar5) //jungler
        barViews.add(binding!!.roleBar6) //durable
        barViews.add(binding!!.roleBar7) //escape
        barViews.add(binding!!.roleBar8) //pusher
        barViews.add(binding!!.roleBar9) //initiator
        var roleIndex = 0
        if (roles != null) {
            for (i in roles.indices) {
                roleIndex = getRoleIndex(roles[i])
                barViews[roleIndex].setLevel(roleLevels?.get(i)!!.toInt())

            }
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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
        binding!!.strPerLevelText.text = strText
        binding!!.agiPerLevelText.text = agiText
        binding!!.intPerLevelText.text = intText
        binding!!.attackDistribution.text = attackDistribution
        binding!!.attackRateText.text = attackRate
        binding!!.attackRangeText.text = attackRange
        binding!!.armorText.text = armorText
        binding!!.magicResistanceText.text = magicResistance
        binding!!.movementSpeedText.text = movementSpeed
        binding!!.turnRateText.text = turnRate
        binding!!.visionText.text = visionText
        binding!!.healthText.text = health
        binding!!.healthRegenText.text = healthRegen
        binding!!.manaText.text = mana
        binding!!.manaRegenText.text = manaRegen
    }

    companion object {
        private val df = DecimalFormat("0.0")
        private val df1 = DecimalFormat("0")
    }
}
