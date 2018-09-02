package com.fpl.fop.fop.features.team.viewModels;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.fpl.fop.fop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class PlayerFooterView extends LinearLayout {

    public PlayerFooterView(Context context) {
        super(context);
        initView();
    }

    @BindView(R.id.total)
    TextView totalView;

    public PlayerFooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PlayerFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlayerFooterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @ModelProp
    public void setTotal(int total) {
        totalView.setText(String.valueOf(total));
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        inflate(getContext(), R.layout.playertable_footer, this);
        ButterKnife.bind(this);
    }
}
