package com.fpl.fop.fop.features.tally.viewModels;

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
import com.fpl.fop.fop.data.model.response.TallyItem;

import butterknife.BindView;
import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class TallyItemView extends LinearLayout {

    private TallyItem bfwTeam;
    private int index;

    @BindView(R.id.position)
    TextView position;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.count)
    TextView count;

    public TallyItemView(Context context) {
        super(context);
        initView();
    }

    public TallyItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TallyItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TallyItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @ModelProp
    public void setTally(TallyItem tallyItem) {
        this.bfwTeam = tallyItem;

        name.setText(tallyItem.name);
        score.setText(String.valueOf(tallyItem.score));
        count.setText(String.valueOf(tallyItem.count));
    }

    @ModelProp
    public void setIndex(int i) {
        this.index = i;
        position.setText(String.valueOf(index));
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        inflate(getContext(), R.layout.tally_table_item, this);
        ButterKnife.bind(this);
    }
}
