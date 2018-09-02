package com.fpl.fop.fop.features.leaguetable;

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
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.base.BaseActivity;
import com.fpl.fop.fop.features.common.ErrorView;
import com.fpl.fop.fop.features.gameweek.GWTableActivity;
import com.fpl.fop.fop.features.gameweek.GWTablePresenter;
import com.fpl.fop.fop.features.gameweek.GWTableView;
import com.fpl.fop.fop.features.leaguetable.adapter.LeagueTableController;
import com.fpl.fop.fop.features.leaguetable.viewModels.BFWTableItemView;
import com.fpl.fop.fop.features.team.TeamActivity;
import com.fpl.fop.fop.injection.component.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class LeagueTableActivity extends BaseActivity implements GWTableView, ErrorView.ErrorListener, BFWTableItemView.ContactSelectionListener{

    private static final int POKEMON_COUNT = 20;

    public static Intent startingIntent(Context context) {
        return new Intent(context, LeagueTableActivity.class);
    }

    @Inject
    GWTablePresenter mainPresenter;

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

    private LeagueTableController leagueTableController;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        context = this;

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> mainPresenter.getPokemon(POKEMON_COUNT));

        leagueTableController = new LeagueTableController(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        leagueTableController.getAdapter()
                .registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        if (positionStart == 0) {
                            linearLayoutManager.scrollToPosition(0);
                        }
                    }
                });
        pokemonRecycler.setLayoutManager(linearLayoutManager);
        pokemonRecycler.setAdapter(leagueTableController.getAdapter());

        errorView.setErrorListener(this);

        mainPresenter.getPokemon(POKEMON_COUNT);
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
    public void showPokemon(List<BfwTeam> pokemon) {
        leagueTableController.setTeams(pokemon, LeagueTableController.SORT_CURRENT_GW);
        pokemonRecycler.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (pokemonRecycler.getVisibility() == View.VISIBLE
                    && leagueTableController.getSpanCount() > 0) {
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
        mainPresenter.getPokemon(POKEMON_COUNT);
    }

    @Override
    public void onClickTeam(BfwTeam bfwTeam) {
        startActivity(TeamActivity.startingIntent(context, bfwTeam));
    }
}
