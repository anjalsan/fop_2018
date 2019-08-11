package com.fpl.fop.fop.features.team.adapter;


import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.fpl.fop.fop.data.model.response.Player;
import com.fpl.fop.fop.features.team.TeamActivity;
import com.fpl.fop.fop.features.team.viewModels.PlayerFooterViewModel_;
import com.fpl.fop.fop.features.team.viewModels.PlayerHeaderViewModel_;
import com.fpl.fop.fop.features.team.viewModels.PlayerItemViewModel_;
import com.fpl.fop.fop.util.PlayerComparator2;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PlayerController extends EpoxyController {
    private final TeamActivity homeActivity;
    private List<Player> players;
    public static int SORT_CURRENT_GW = 0, SORT_OVERALL_GW = 1;

    @AutoModel
    PlayerHeaderViewModel_ bfwHeaderViewModel_;

    @AutoModel
    PlayerFooterViewModel_ playerFooterViewModel_;


    public long sub_id, cap_id;
    private float total;

    @Inject
    public PlayerController(TeamActivity homeActivity) {
        this.homeActivity = homeActivity;
        setDebugLoggingEnabled(true);
    }

    public void setPlayers(List<Player> players, long fpl_id, long sub_id, float total) {
        this.players = players;
        this.cap_id = fpl_id;
        this.sub_id = sub_id;
        this.total = total;
        this.players = sortContacts(players);
        requestModelBuild();
    }

    private List<Player> sortContacts(List<Player> players) {
        Collections.sort(players, new PlayerComparator2.gwRank());
        return players;
    }

    @Override
    protected void buildModels() {

        bfwHeaderViewModel_
                .addTo(this);

        if (players != null) {
            for (int i=0; i< players.size(); i++) {
                new PlayerItemViewModel_()
                    .id(players.get(i).id)
                    .index(i+1)
                    .player(players.get(i))
                    .capId(cap_id)
                    .subId(sub_id)
                    .listener(homeActivity)
                    .addTo(this);
            }
        }

        playerFooterViewModel_
                .total(total)
                .addTo(this);
    }
}
