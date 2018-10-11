package com.fpl.fop.fop.mvrx.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.fpl.fop.fop.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class VegtallyHeader @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val rootview by lazy { findViewById<LinearLayout>(R.id.rootview) }

    init {
        View.inflate(context, R.layout.task_item_header, this)
    }

    /**
     * When not loading, the progress bar is invisible so it still takes up the same space
     * at the top of the screen instead of the views snapping
     */
    @ModelProp
    fun setLoading(loading: Boolean) {
        if (!loading) {
            rootview.visibility = View.VISIBLE
        } else {
            rootview.visibility = View.GONE
        }
    }
}