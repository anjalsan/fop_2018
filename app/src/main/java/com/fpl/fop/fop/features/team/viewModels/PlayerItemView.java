package com.fpl.fop.fop.features.team.viewModels;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.CallbackProp;
import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;
import com.fpl.fop.fop.R;
import com.fpl.fop.fop.data.model.response.Player;

import butterknife.BindView;
import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class PlayerItemView extends LinearLayout {
    public interface ContactSelectionListener {
        void onClickTeam(Player bfwTeam);
    }

    private Player player;
    private long sub_id, captain_id = 0;

    @BindView(R.id.position)
    TextView position;

    @BindView(R.id.team)
    TextView playerview;

    @BindView(R.id.point)
    TextView point;

    public PlayerItemView(Context context) {
        super(context);
        initView();
    }

    public PlayerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PlayerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlayerItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @ModelProp
    public void setPlayer(Player player) {
        this.player = player;

        playerview.setText(player.first_name);
        point.setText(String.valueOf(player.live_gw_score));
        setColor();
    }

    @ModelProp
    public void setIndex(int i) {
        position.setText(String.valueOf(i));
    }

    @ModelProp
    public void setSubId(long i) {
        this.sub_id = i;
        setColor();
    }

    @ModelProp
    public void setCapId(long i) {
        this.captain_id = i;
        setColor();
    }

    private void setColor() {
        if (player != null) {
            if (player.fpl_id == captain_id) {
                playerview.setTextColor(ContextCompat.getColor(getContext(), R.color.primary));
            } else if (player.fpl_id == sub_id) {
                playerview.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            } else {
                playerview.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }
        }
    }

    @CallbackProp
    public void setListener(@Nullable ContactSelectionListener listener) {
        if (listener != null) {
            setOnClickListener((OnClickListener) v -> listener.onClickTeam(player));
        }
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        inflate(getContext(), R.layout.player_item, this);
        ButterKnife.bind(this);
    }
}
