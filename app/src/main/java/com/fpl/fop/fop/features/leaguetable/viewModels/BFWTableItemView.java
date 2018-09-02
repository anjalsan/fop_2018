package com.fpl.fop.fop.features.leaguetable.viewModels;

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
import com.fpl.fop.fop.data.model.response.Chip;

import butterknife.BindView;
import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class BFWTableItemView extends LinearLayout {
    public interface ContactSelectionListener {
        void onClickTeam(BfwTeam bfwTeam);
    }

    private BfwTeam bfwTeam;
    private int index;

    @BindView(R.id.position)
    TextView position;

    @BindView(R.id.team)
    TextView team;

    @BindView(R.id.win)
    TextView win;

    @BindView(R.id.gw)
    TextView gw;

    @BindView(R.id.chip)
    TextView chip;

    @BindView(R.id.bonus)
    TextView bonus;

    @BindView(R.id.point)
    TextView point;

    public BFWTableItemView(Context context) {
        super(context);
        initView();
    }

    public BFWTableItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BFWTableItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BFWTableItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @ModelProp
    public void setTeam(BfwTeam bfwTeam) {
        this.bfwTeam = bfwTeam;

        team.setText(bfwTeam.name);
        point.setText(String.valueOf(bfwTeam.total_points));
        bonus.setText(String.valueOf(bfwTeam.no_of_bonus));
        win.setText(String.valueOf(bfwTeam.no_of_wins));

        String chipInfo = "";
        if (bfwTeam.chips != null) {
            for (Chip chip : bfwTeam.chips) {
                if (!chip.is_used) {
                    chipInfo = String.format("%sx", chipInfo);
                } else {
                    chipInfo = String.format("%s✓", chipInfo);
                }
            }
            chip.setText(chipInfo);
        }

        if (bfwTeam.game_week != null) {
            gw.setText(String.valueOf(bfwTeam.game_week.total_score));
        }
    }

    @ModelProp
    public void setIndex(int i) {
        this.index = i;
        position.setText(String.valueOf(i));
    }

    @CallbackProp
    public void setListener(@Nullable ContactSelectionListener listener) {
        if (listener != null) {
            setOnClickListener((OnClickListener) v -> listener.onClickTeam(bfwTeam));
        }
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        inflate(getContext(), R.layout.league_table_item, this);
        ButterKnife.bind(this);
    }
}
