package com.yigitsezer.dotacompanion.fragments.encyclopedia.items

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.dotacompanion.DotaApplication
import com.yigitsezer.dotacompanion.databinding.FragmentItemDescriptionDialogueBinding
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder
import kotlin.math.absoluteValue

//When you click so many items at once and it shows multiple dialogs on top of each other
//the backstack gets all fucked up, either fix this behaviour or prevent multiple dialogs
class ItemDescriptionDialogueFragment : DialogFragment() {

    var binding: FragmentItemDescriptionDialogueBinding? = null
    var itemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = requireArguments().getInt("item_id")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDescriptionDialogueBinding.inflate(inflater, container, false)

        val db = (activity?.application as DotaApplication).getDb()
        val item = db?.itemDao()?.getItem(this.itemId)
        binding?.let {
            it.itemDescriptionDialogView.descriptionItemName.text = item?.localized_name
            it.itemDescriptionDialogView.descriptionItemImage.setImageResource(resources.getIdentifier(item?.icon?.length?.minus(4)?.let { item.icon?.substring(23, it) }, "drawable", context?.packageName))
            it.itemDescriptionDialogView.itemCostText.text = item?.cost.toString()
            it.itemDescriptionDialogView.descriptionRecyclerView.layoutManager = GridLayoutManager(this.context, 1, RecyclerView.VERTICAL, false)
            if (item?.neutral_tier != null) {
                it.itemDescriptionDialogView.itemCostText.visibility = View.INVISIBLE
                it.itemDescriptionDialogView.itemCostImage.visibility = View.INVISIBLE
                it.itemDescriptionDialogView.itemNeutralTierText.visibility = View.VISIBLE
                it.itemDescriptionDialogView.itemNeutralTierText.text = "Tier " + item?.neutral_tier + " Neutral Item"
            }
            val json = JSONArray(item?.ability_special)
            val stringBuilder = StringBuilder()
            for (i in 0 until json.length()) {
                var curObj = json.get(i) as JSONObject
                //TODO: I should be able to come up with a better solution but does it really matter that much?
                if (curObj.has("footer") && curObj.has("header")) {
                    Log.d("HELLOW", ""+curObj)
                    var value = curObj.get("value").toString()
                    if (curObj.get("header") == "-")
                        value = value.toInt().absoluteValue.toString()
                    stringBuilder.append("<b>")
                            .append(curObj.get("header"))
                            .append(" ")
                            .append(value.toString().replace(" ", " / "))
                            .append("</b> ")
                            .append(curObj.get("footer"))
                            .append("\n")
                } else if (curObj.has("header") && !curObj.has("footer")) {
                    stringBuilder.append(curObj.get("header"))
                            .append(" <b>")
                            .append(curObj.get("value"))
                            .append("</b>\n")
                }
            }
            if (stringBuilder.toString().isEmpty()) {
                it.itemDescriptionDialogView.itemStatGain.visibility = View.GONE
            } else {
                it.itemDescriptionDialogView.itemStatGain.text = HtmlCompat.fromHtml(stringBuilder.toString().trim().replace(Regex("\\n"), "<br>"), HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
            it.itemDescriptionDialogView.descriptionRecyclerView.adapter = ItemDescriptionAdapter(context, item)
        }
        return binding!!.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        findNavController().navigateUp()
        super.onDismiss(dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
