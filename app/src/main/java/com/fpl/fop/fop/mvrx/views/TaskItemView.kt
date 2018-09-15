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

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TaskItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleView by lazy { findViewById<TextView>(R.id.title) }

    init {
        inflate(context, R.layout.task_item, this)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
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