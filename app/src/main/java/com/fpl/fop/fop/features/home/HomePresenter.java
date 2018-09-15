package com.fpl.fop.fop.features.home;

import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.features.base.BasePresenter;
import com.fpl.fop.fop.features.gameweek.GWTableView;
import com.fpl.fop.fop.injection.ConfigPersistent;
import com.fpl.fop.fop.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

@ConfigPersistent
public class HomePresenter extends BasePresenter<HomeContractView> {

    private final DataManager dataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(HomeContractView mvpView) {
        super.attachView(mvpView);
    }

    public void clickGw() {
        getView().clickGWLive();
    }

    public void clickTable() {
        getView().clickTable();
    }

    public void clickOthers() {
        getView().clickOther();
    }
}
