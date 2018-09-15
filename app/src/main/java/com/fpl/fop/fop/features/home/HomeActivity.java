package com.fpl.fop.fop.features.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fpl.fop.fop.R;
import com.fpl.fop.fop.features.base.BaseActivity;
import com.fpl.fop.fop.features.gameweek.GWTableActivity;
import com.fpl.fop.fop.features.gameweek.GWTablePresenter;
import com.fpl.fop.fop.features.gameweek.GWTableView;
import com.fpl.fop.fop.features.leaguetable.LeagueTableActivity;
import com.fpl.fop.fop.injection.component.ActivityComponent;
import com.fpl.fop.fop.mvrx.TallyDetailsActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeContractView {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.gw)
    View gw;

    @BindView(R.id.table)
    View table;

    @BindView(R.id.veg_tally)
    View vegTally;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        context = this;

        gw.setOnClickListener(v -> homePresenter.clickGw());
        table.setOnClickListener(v -> homePresenter.clickTable());
        vegTally.setOnClickListener(v -> homePresenter.clickOthers());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        homePresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        homePresenter.detachView();
    }

    @Override
    public void clickGWLive() {
        startActivity(GWTableActivity.startingIntent(context));
    }

    @Override
    public void clickTable() {
        startActivity(LeagueTableActivity.startingIntent(context));
    }

    @Override
    public void clickOther() {
        startActivity(TallyDetailsActivity.startingIntent(context));
    }
}
