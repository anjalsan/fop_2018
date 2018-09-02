package com.fpl.fop.fop.features.gameweek.viewModels;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.CallbackProp;
import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.fpl.fop.fop.R;
import com.fpl.fop.fop.data.model.response.BfwTeam;

import butterknife.BindView;
import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class BFWItemView extends LinearLayout {
    public interface ContactSelectionListener {
        void onClickTeam(BfwTeam bfwTeam);
    }

    private BfwTeam bfwTeam;
    private int index;

    @BindView(R.id.position)
    TextView position;

    @BindView(R.id.team)
    TextView team;

    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.bonus)
    TextView bonus;

    @BindView(R.id.point)
    TextView point;

    public BFWItemView(Context context) {
        super(context);
        initView();
    }

    public BFWItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BFWItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BFWItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @ModelProp
    public void setTeam(BfwTeam bfwTeam) {
        this.bfwTeam = bfwTeam;

        team.setText(bfwTeam.name);
        if (bfwTeam.game_week != null){
            score.setText(String.valueOf(bfwTeam.game_week.total_score));
            point.setText(String.valueOf(bfwTeam.game_week.points));
            bonus.setText(bfwTeam.game_week.bonus_gained ? "✓" : "x");
        }

    }

    @ModelProp
    public void setIndex(int i) {
        this.index = i;
        position.setText(String.valueOf(index));
    }

    @CallbackProp
    public void setListener(@Nullable ContactSelectionListener listener) {
        if (listener != null) {
            setOnClickListener((OnClickListener) v -> listener.onClickTeam(bfwTeam));
        }
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        inflate(getContext(), R.layout.gw_table_item, this);
        ButterKnife.bind(this);
    }
}
