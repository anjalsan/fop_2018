package com.fpl.fop.fop.features.team;

import com.fpl.fop.fop.data.DataManager;
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.base.BasePresenter;
import com.fpl.fop.fop.injection.ConfigPersistent;

import java.io.Serializable;

import javax.inject.Inject;

@ConfigPersistent
public class TeamPresenter extends BasePresenter<TeamContractView> {

    private final DataManager dataManager;
    private BfwTeam bfwTeam;

    @Inject
    public TeamPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(TeamContractView mvpView) {
        super.attachView(mvpView);
    }

    public void setTeam(BfwTeam bfwTeam) {
        this.bfwTeam = bfwTeam;
        getView().setTeamInfo(bfwTeam);
    }

    public void gotoVegTally() {
        getView().gotoVegTally(bfwTeam.game_week.game_week.fpl_gw, bfwTeam.id, bfwTeam.name);
    }
}
