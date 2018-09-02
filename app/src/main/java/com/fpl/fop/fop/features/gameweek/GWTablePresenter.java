package com.fpl.fop.fop.features.gameweek;

import javax.inject.Inject;

import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.features.base.BasePresenter;
import com.fpl.fop.fop.injection.ConfigPersistent;
import com.fpl.fop.fop.util.rx.scheduler.SchedulerUtils;

import io.reactivex.disposables.Disposable;

@ConfigPersistent
public class GWTablePresenter extends BasePresenter<GWTableView> {

    private final DataManager dataManager;
    private Disposable disposable;

    @Inject
    public GWTablePresenter(DataManager dataManager) {
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
