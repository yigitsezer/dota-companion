package com.yigitsezer.dotacompanion.fragments.encyclopedia.heroes.hero_info

import android.app.Activity
import com.yigitsezer.dotacompanion.fragments.encyclopedia.items.ItemDescriptionAdapter

import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.join
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.text.HtmlCompat
import androidx.core.text.TextUtilsCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.DotaApplication
import com.yigitsezer.dotacompanion.data.dotabase.Ability
import com.yigitsezer.dotacompanion.data.dotabase.Hero
import com.yigitsezer.dotacompanion.databinding.FragmentAbilityDescriptionDialogueBinding
import com.yigitsezer.dotacompanion.databinding.FragmentItemDescriptionDialogueBinding
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

class AbilityDescriptionDialogueFragment : DialogFragment() {

    var binding: FragmentAbilityDescriptionDialogueBinding? = null
    val hero: Hero? = null
    var ability: Ability? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = (context as Activity?)!!.application as DotaApplication
        val abilityId = requireArguments().getInt("ability_id")
        ability = application.getDb()?.abilityDao()!!.getAbility(abilityId)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbilityDescriptionDialogueBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.abilityDescriptionView?.let { descView ->
            ability?.cooldown?.let {
                descView.abilityDescriptionCdText.text = it.replace(" ", " / ")
            } ?: run {
                descView.abilityDescriptionCdIcon.visibility = View.GONE
                descView.abilityDescriptionCdText.visibility = View.GONE
            }
            ability?.manaCost?.let {
                descView.abilityDescriptionManaText.text = it.replace(" ", " / ")
            } ?: run {
                descView.abilityDescriptionManaIcon.visibility = View.GONE
                descView.abilityDescriptionManaText.visibility = View.GONE
            }

            descView.abilityDescriptionName.text = ability?.localizedName


            var behaviourData: String? = null
            ability?.behavior?.let {
                behaviourData = it
            }
            var behaviours: ArrayList<String> = ArrayList()

            // TODO: Colors for damage type and dispelability
            // FIXME: I am not very good at programming
            behaviourData?.let {
                if (it.contains("passive"))
                    behaviours.add("Passive")
                if (it.contains("aura"))
                    behaviours.add("Aura")
                if (it.contains("unit_target"))
                    behaviours.add("Target Unit")
                if (it.contains("point"))
                    behaviours.add("Target point")
                if (it.contains("aoe"))
                    behaviours.add("Target area")
                if (it.contains("no_target"))
                    behaviours.add("No Target")
                if (it.contains("toggle"))
                    behaviours.add("Toggle")
                if (it.contains("autocast"))
                    behaviours.add("Auto-Cast") //every ability that can be shown with autocast is also unit target
                if (it.contains("channelled"))
                    behaviours.add("Channelled")
            } //TODO: see if there is more

            val sb = StringBuilder()
            if (behaviours.isNotEmpty()) {
                sb.append("ABILITY: ").append(join(", ", behaviours))
            }


            ability?.targetTeam?.let {
                if (it.isNotEmpty()) {
                    sb.append("\nAFFECTS: ")
                    if (it == "friendly")
                        sb.append("Allied Units")
                    else if (it == "enemy")
                        sb.append("Enemy Units")
                    else
                        sb.append("Heroes")
                }
            }
            ability?.damageType?.let { sb.append("\nDAMAGE TYPE: ").append(it.capitalize()) }
            //ability?.targetTeam?.let { sb.append("\nTARGETS: ").append(it.capitalize()) }
            ability?.spellImmunity?.let {
                sb.append("\nPIERCES SPELL IMMUNITY: ")
                        .append(if (it.contains(Regex("^(yes|no)\$"))) it.capitalize() else "Yes")
            }
            //I guess this works, see to it
            ability?.dispellable?.let { sb.append("\nDISPELLABLE: ").append(if (it.contains("strong")) { "Strong Dispels Only" } else {it.capitalize()}) }
            descView.abilityDescriptionSummary.text = sb.toString().trim()
            var description = ability?.description
            if (ability?.aghanim?.isNotEmpty()!!) {
                description += "\n\n<font color=#82B46E>Aghanim Upgrade: ${ability?.aghanim}</font>"
            }
            descView.abilityDescriptionDescription.text = HtmlCompat.fromHtml(description?.replace(Regex("\\*\\*(.*?)\\*\\*"), "<b>$1</b>").toString().replace(Regex("\\n"), "<br>"), HtmlCompat.FROM_HTML_MODE_LEGACY)
            //Bottom
            sb.setLength(0)
            ability?.damage?.let { if (it != "0") {sb.append("<b><font color=#525e6d>DAMAGE: </font></b>").append(it.replace(" ", " / ")).append("\n") }}
            ability?.duration?.let { if (it != "0") {sb.append("<b><font color=#525e6d>DURATION: </font></b>").append(it.replace(" ", " / ")).append("\n")} }


            val abilitySpecial = JSONArray(ability?.abilitySpecial)
            for (i in 0 until abilitySpecial.length()) {
                val curObj = abilitySpecial.get(i) as JSONObject
                if (curObj.has("header")) {
                    if (curObj.toString().contains("scepter", true))
                        sb.append("<b><font color=#82B46E>${curObj.get("header")}</font></b>").append(" ").append(curObj.get("value").toString().replace(" ", " / ")).append("\n")
                    else
                        sb.append("<b><font color=#525e6d>${curObj.get("header")}</font></b>").append(" ").append(curObj.get("value").toString().replace(" ", " / ")).append("\n")

                    /*
                    //remove if, investigate silencer global silence duration behaviour
                    if (curObj.get("header") == "DURATION:" && curObj.get("value").toString().isEmpty()) {
                        sb.append(curObj.get("header")).append(" ").append(curObj.get("value").toString().replace(" ", " / ")).append("\n")
                    } else {
                        sb.append(curObj.get("header")).append(" ").append(curObj.get("value").toString().replace(" ", " / ")).append("\n")
                    }
                     */
                }
            }
            //#555d6c
            ability?.castPoint?.let { sb.append("<b><font color=#525e6d>CAST POINT: </font></b>").append(it).append("s") }

            descView.abilityDescriptionSpecial.text = HtmlCompat.fromHtml(sb.toString().trim().replace(Regex("\\n"), "<br>"), HtmlCompat.FROM_HTML_MODE_LEGACY)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}
