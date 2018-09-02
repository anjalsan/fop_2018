package com.fpl.fop.fop.features.tally.adapter;


import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.fpl.fop.fop.data.model.response.TallyItem;
import com.fpl.fop.fop.features.tally.TallyActivity;
import com.fpl.fop.fop.features.tally.viewModels.TallyHeaderViewModel_;
import com.fpl.fop.fop.features.tally.viewModels.TallyItemViewModel_;
import com.fpl.fop.fop.util.TallyComparator2;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class TallyController extends EpoxyController {
    private final TallyActivity homeActivity;
    private List<TallyItem> tallyItems;
    public static int SORT_CURRENT_GW = 0, SORT_OVERALL_GW = 1;

    @AutoModel
    TallyHeaderViewModel_ tallyHeaderViewModel_;

    @Inject
    public TallyController(TallyActivity homeActivity) {
        this.homeActivity = homeActivity;
        setDebugLoggingEnabled(true);
    }

    public void setTeams(List<TallyItem> teams, int sortType) {
        this.tallyItems = sortContacts(teams, sortType);
        this.tallyItems = addIds(teams);
        requestModelBuild();
    }

    private List<TallyItem> addIds(List<TallyItem> tallyItems) {
        List<TallyItem> temp = tallyItems;
        for (int i=0; i< temp.size(); i++) {
            temp.get(i).id = i;
        }
        return temp;
    }

    private List<TallyItem> sortContacts(List<TallyItem> bfwTeams, int sortType) {
        Collections.sort(bfwTeams, new TallyComparator2.gwRank());
        return bfwTeams;
    }

    @Override
    protected void buildModels() {

        tallyHeaderViewModel_
                .addTo(this);

        if (tallyItems != null && tallyItems.size() > 0) {
            for (int i=0; i< tallyItems.size(); i++) {
                new TallyItemViewModel_()
                    .id(tallyItems.get(i).id)
                    .index(i+1)
                    .tally(tallyItems.get(i))
                    .addTo(this);
            }
        }
    }
}
