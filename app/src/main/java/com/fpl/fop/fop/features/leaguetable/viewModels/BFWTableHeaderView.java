package com.fpl.fop.fop.features.leaguetable.viewModels;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.airbnb.epoxy.ModelView;
import com.fpl.fop.fop.R;

import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class BFWTableHeaderView extends LinearLayout {

    public BFWTableHeaderView(Context context) {
        super(context);
        initView();
    }

    public BFWTableHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BFWTableHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BFWTableHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        inflate(getContext(), R.layout.bfw_leaguetable_header, this);
        ButterKnife.bind(this);
    }
}
