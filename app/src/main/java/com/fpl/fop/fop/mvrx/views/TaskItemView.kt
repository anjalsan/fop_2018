package com.fpl.fop.fop.mvrx.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.fpl.fop.fop.R
import com.fpl.fop.fop.data.model.response.TallyItem

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TaskItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleView by lazy { findViewById<TextView>(R.id.title) }

    private val whiteWalker by lazy { findViewById<TextView>(R.id.whiteWalker) }
    private val brotherhood by lazy { findViewById<TextView>(R.id.brotherhood) }
    private val dothraki by lazy { findViewById<TextView>(R.id.dothraki) }
    private val valyrians by lazy { findViewById<TextView>(R.id.valyrians) }
    private val targaryens by lazy { findViewById<TextView>(R.id.targaryens) }
    private val nightsWatch by lazy { findViewById<TextView>(R.id.nightsWatch) }
    private val lannisters by lazy { findViewById<TextView>(R.id.lannisters) }
    private val starks by lazy { findViewById<TextView>(R.id.starks) }
    private val facelessMen by lazy { findViewById<TextView>(R.id.facelessMen) }
    private val kingslayers by lazy { findViewById<TextView>(R.id.kingslayers) }

    init {
        inflate(context, R.layout.task_item, this)
    }

    @ModelProp
    fun setTitle(title: TallyItem) {
        titleView.text = title.name

        whiteWalker.text = title.whiteWalker.toString()
        brotherhood.text = title.brotherhood.toString()
        dothraki.text = title.dothraki.toString()
        valyrians.text = title.valyrians.toString()
        targaryens.text = title.targaryens.toString()
        nightsWatch.text = title.nightsWatch.toString()
        lannisters.text = title.lannisters.toString()
        starks.text = title.starks.toString()
        facelessMen.text = title.facelessMen.toString()
        kingslayers.text = title.kingslayers.toString()
    }

    @ModelProp
    fun setChecked(checked: Boolean) {
        var isChecked = checked
    }

    @CallbackProp
    fun onClickListener(listener: View.OnClickListener?) {
        setOnClickListener(listener)
    }
}