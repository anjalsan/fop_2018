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

        render(whiteWalker, title.whiteWalker)
        render(brotherhood, title.brotherhood)
        render(dothraki, title.dothraki)
        render(valyrians, title.valyrians)
        render(targaryens, title.targaryens)
        render(nightsWatch, title.nightsWatch)
        render(lannisters, title.lannisters)
        render(starks, title.starks)
        render(facelessMen, title.facelessMen)
        render(kingslayers, title.kingslayers)
    }

    private fun render(view: TextView?, count: Float) {
        if ((count * 10) % 10 == 0F) {
            view?.text = count.toInt().toString()
        } else {
            view?.text = count.toString()
        }
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