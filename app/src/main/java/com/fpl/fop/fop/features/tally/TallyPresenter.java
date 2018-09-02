package com.fpl.fop.fop.features.tally;

import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.features.base.BasePresenter;
import com.fpl.fop.fop.injection.ConfigPersistent;
import com.fpl.fop.fop.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@ConfigPersistent
public class TallyPresenter extends BasePresenter<TallyView> {

    private final DataManager dataManager;
    private Disposable disposable;

    @Inject
    public TallyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(TallyView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void getPokemon(String limit) {
        checkViewAttached();
        getView().showProgress(true);
        disposable = dataManager
                .getVegList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemons -> {
                            getView().showProgress(false);
                            getView().showTallys(pokemons);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
