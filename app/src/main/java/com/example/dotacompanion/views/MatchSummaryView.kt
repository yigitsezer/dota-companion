package com.example.dotacompanion.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dotacompanion.data.MatchSummaryData

/**
 * TODO: document your custom view class.
 */
class MatchSummaryView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context!!, attrs) {
    var matchSummaryData: MatchSummaryData? = null
}