package com.fpl.fop.fop.features.team;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.fpl.fop.fop.R;
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.data.model.response.Player;
import com.fpl.fop.fop.features.base.BaseActivity;
import com.fpl.fop.fop.features.tally.TallyActivity;
import com.fpl.fop.fop.features.team.adapter.PlayerController;
import com.fpl.fop.fop.features.team.viewModels.PlayerItemView;
import com.fpl.fop.fop.injection.component.ActivityComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;

public class TeamActivity extends BaseActivity implements TeamContractView, PlayerItemView.ContactSelectionListener {

    public static String EXTRA_TEAM = "extra_team";
    private PlayerController playerController;

    public static Intent startingIntent(Context context, BfwTeam bfwTeam) {
        Intent intent = new Intent(context, TeamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_TEAM, bfwTeam);
        intent.putExtra(EXTRA_TEAM, bundle);
        return intent;
    }

    @Inject
    TeamPresenter teamPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.point)
    TextView point;

    @BindView(R.id.bonus)
    TextView bonus;

    @BindView(R.id.win)
    TextView win;

    @BindView(R.id.gw)
    TextView gw;

    @BindView(R.id.manager)
    TextView manager;

    @BindView(R.id.chip1)
    TextView chip1;

    @BindView(R.id.chip2)
    TextView chip2;

    @BindView(R.id.chip3)
    TextView chip3;

    @BindView(R.id.chip4)
    TextView chip4;

    @BindView(R.id.captain)
    TextView captain;

    @BindView(R.id.sub)
    TextView sub;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.last_update)
    TextView lastUpdate;

    @BindView(R.id.vegTally_container)
    View vegTallyContainer;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private Context context;
    private int current_gw = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        toolbar.setTitle("White Walkers");
        setTitle("White Walkers");
        setTitle(getString(R.string.app_name));
        context = this;

        playerController = new PlayerController(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        playerController.getAdapter()
                .registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        if (positionStart == 0) {
                            linearLayoutManager.scrollToPosition(0);
                        }
                    }
                });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(playerController.getAdapter());

        if (getIntent().getBundleExtra(EXTRA_TEAM) != null && getIntent().getBundleExtra(EXTRA_TEAM).getSerializable(EXTRA_TEAM) != null) {
            teamPresenter.setTeam((BfwTeam) getIntent().getBundleExtra(EXTRA_TEAM).getSerializable(EXTRA_TEAM));
        } else {
            finish();
        }

        vegTallyContainer.setOnClickListener(v -> {
            teamPresenter.gotoVegTally();
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_team;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        teamPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        teamPresenter.detachView();
    }

    @Override
    public void setTeamInfo(BfwTeam bfwTeam) {
        collapsingToolbarLayout.setTitle(bfwTeam.name);
        point.setText(String.valueOf(bfwTeam.total_points));
        bonus.setText(String.valueOf(bfwTeam.no_of_bonus));
        win.setText(String.valueOf(bfwTeam.no_of_wins));
        gw.setText(String.valueOf(bfwTeam.game_week.total_score));
        manager.setText(String.format("%s %s", bfwTeam.manager.first_name, bfwTeam.manager.last_name));
        if (bfwTeam.game_week.captain != null) {
            captain.setText(String.format("%s %s", "Captain: ", bfwTeam.game_week.captain.first_name));
        }
        if (bfwTeam.game_week.substitute != null) {
            sub.setText(String.format("%s %s", "Substitute: ", bfwTeam.game_week.substitute.first_name));
        }

        if (bfwTeam.updated_at != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("MMM dd hh:mm a");
                sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

                Date d = sdf.parse(bfwTeam.updated_at);
                String formattedTime = output.format(d);
                lastUpdate.setText(String.format("Last Updated: %s", formattedTime));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if (bfwTeam.chips != null &&  bfwTeam.chips.size() > 3) {
            if (bfwTeam.chips.get(0).is_used) {
                chip1.setText("GW: "+String.valueOf(bfwTeam.chips.get(0).used_gw));
                chip1.setTextColor(Color.BLACK);
            } else {
                chip1.setText("Not Used");
            }

            if (bfwTeam.chips.get(1).is_used) {
                chip2.setText("GW: "+String.valueOf(bfwTeam.chips.get(1).used_gw));
                chip2.setTextColor(Color.BLACK);
            } else {
                chip2.setText("Not Used");
            }

            if (bfwTeam.chips.get(2).is_used) {
                chip3.setText("GW: "+String.valueOf(bfwTeam.chips.get(2).used_gw));
                chip3.setTextColor(Color.BLACK);
            } else {
                chip3.setText("Not Used");
            }

            if (bfwTeam.chips.get(3).is_used) {
                chip4.setText("GW: "+String.valueOf(bfwTeam.chips.get(3).used_gw));
                chip4.setTextColor(Color.BLACK);
            } else {
                chip4.setText("Not Used");
            }
        }

        playerController.setPlayers(bfwTeam.players, bfwTeam.game_week.captain.fpl_id, bfwTeam.game_week.substitute.fpl_id, bfwTeam.game_week.total_score);

        Glide.with(context)
                .load(bfwTeam.image_url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .apply(RequestOptions.placeholderOf(R.drawable.team_placeholder).error(R.drawable.team_placeholder))
                .into(image);

        if (bfwTeam.game_week != null && bfwTeam.game_week.game_week != null) {
            current_gw = bfwTeam.game_week.game_week.fpl_gw;
        }


    }

    @Override
    public void gotoVegTally(int fpl_gw, int id, String name) {
        startActivity(TallyActivity.startingIntent(context, fpl_gw, id, name));
    }

    @Override
    public void onClickTeam(Player bfwTeam) {
        if (current_gw == 0) {
            return;
        }
        String link = String.format("https://fantasy.premierleague.com/a/team/%s/event/%s", String.valueOf(bfwTeam.fpl_id), String.valueOf(current_gw));
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(this.getResources()
                        .getColor(R.color.primary))
                .setShowTitle(true)
                .build();

        customTabsIntent.launchUrl(this, Uri.parse(link));
    }
}
