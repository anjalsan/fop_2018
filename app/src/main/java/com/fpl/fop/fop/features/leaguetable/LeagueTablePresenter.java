package com.fpl.fop.fop.features.leaguetable;

import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.features.base.BasePresenter;
import com.fpl.fop.fop.features.gameweek.GWTableView;
import com.fpl.fop.fop.injection.ConfigPersistent;
import com.fpl.fop.fop.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@ConfigPersistent
public class LeagueTablePresenter extends BasePresenter<GWTableView> {

    private final DataManager dataManager;
    private Disposable disposable;

    @Inject
    public LeagueTablePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(GWTableView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getView().showProgress(true);
        disposable = dataManager
                .getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemons -> {
                            getView().showProgress(false);
                            getView().showPokemon(pokemons);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
