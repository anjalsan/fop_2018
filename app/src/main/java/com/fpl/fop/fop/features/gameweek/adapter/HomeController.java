package com.fpl.fop.fop.features.gameweek.adapter;


import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.features.gameweek.GWTableActivity;
import com.fpl.fop.fop.features.gameweek.viewModels.BFWHeaderViewModel_;
import com.fpl.fop.fop.features.gameweek.viewModels.BFWItemViewModel_;
import com.fpl.fop.fop.util.CustomerComparator2;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class HomeController extends EpoxyController {
    private final GWTableActivity homeActivity;
    private List<BfwTeam> bfwTeams;
    public static int SORT_CURRENT_GW = 0, SORT_OVERALL_GW = 1;

    @AutoModel
    BFWHeaderViewModel_ bfwHeaderViewModel_;

    @Inject
    public HomeController(GWTableActivity homeActivity) {
        this.homeActivity = homeActivity;
        setDebugLoggingEnabled(true);
    }

    public void setTeams(List<BfwTeam> teams, int sortType) {
        this.bfwTeams = teams;
        this.bfwTeams = sortContacts(bfwTeams, sortType);
        requestModelBuild();
    }

    private List<BfwTeam> sortContacts(List<BfwTeam> bfwTeams, int sortType) {
        Collections.sort(bfwTeams, new CustomerComparator2.gwRank());
        return bfwTeams;
    }

    @Override
    protected void buildModels() {

        bfwHeaderViewModel_
                .addTo(this);

        if (bfwTeams != null && bfwTeams.size() > 0) {
            for (int i=0; i< bfwTeams.size(); i++) {
                new BFWItemViewModel_()
                    .id(bfwTeams.get(i).id)
                    .index(i+1)
                    .team(bfwTeams.get(i))
                    .listener(homeActivity)
                    .addTo(this);
            }
        }
    }
}
