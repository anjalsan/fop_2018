package com.fpl.fop.fop.features.team;

import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.base.MvpView;

public interface TeamContractView extends MvpView {

    void setTeamInfo(BfwTeam bfwTeam);

    void gotoVegTally(int fpl_gw, int id, String name);
}