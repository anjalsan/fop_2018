package com.fpl.fop.fop.features.tally;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.fpl.fop.fop.R;
import com.fpl.fop.fop.data.model.response.TallyItem;
import com.fpl.fop.fop.features.base.BaseActivity;
import com.fpl.fop.fop.features.common.ErrorView;
import com.fpl.fop.fop.features.tally.adapter.TallyController;
import com.fpl.fop.fop.injection.component.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class TallyActivity extends BaseActivity implements TallyView, ErrorView.ErrorListener{

    public static String EXTRA_GW = "extra_gw";
    public static String EXTRA_ID = "extra_id";
    public static String EXTRA_NAME = "extra_name";
    private static final int POKEMON_COUNT = 20;

    public static Intent startingIntent(Context context, int gw, int team_id, String name) {
        Intent intent = new Intent(context, TallyActivity.class);
        intent.putExtra(EXTRA_GW, gw);
        intent.putExtra(EXTRA_ID, team_id);
        intent.putExtra(EXTRA_NAME, name);
        return intent;
    }

    @Inject
    TallyPresenter mainPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_pokemon)
    RecyclerView pokemonRecycler;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TallyController homeController;
    private Context context;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        context = this;
        setTitle(getIntent().getStringExtra(EXTRA_NAME));

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> mainPresenter.getPokemon(url));

        homeController = new TallyController(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        homeController.getAdapter()
                .registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        if (positionStart == 0) {
                            linearLayoutManager.scrollToPosition(0);
                        }
                    }
                });
        pokemonRecycler.setLayoutManager(linearLayoutManager);
        pokemonRecycler.setAdapter(homeController.getAdapter());

        errorView.setErrorListener(this);

        String gw = String.valueOf(getIntent().getIntExtra(EXTRA_GW, 0));
        String id = String.valueOf(getIntent().getIntExtra(EXTRA_ID, 0));
        String url = getUrl(gw, id);
        mainPresenter.getPokemon(url);
    }

    private String getUrl(String gw, String id) {
        this.url = String.format("hfh/api/veg_tally/%s/%s", gw, id);
        return url;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void showTallys(List<TallyItem> pokemon) {
        homeController.setTeams(pokemon, TallyController.SORT_CURRENT_GW);
        pokemonRecycler.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (pokemonRecycler.getVisibility() == View.VISIBLE
                    && homeController.getSpanCount() > 0) {
                swipeRefreshLayout.setRefreshing(true);
            } else {
                progressBar.setVisibility(View.VISIBLE);

                pokemonRecycler.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }

            errorView.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        pokemonRecycler.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void onReloadData() {
        mainPresenter.getPokemon(url);
    }
}
