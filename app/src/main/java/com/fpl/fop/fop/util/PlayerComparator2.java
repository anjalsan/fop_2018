package com.fpl.fop.fop.util;

import com.fpl.fop.fop.data.model.response.BfwTeam;
import com.fpl.fop.fop.data.model.response.Player;

import java.util.Comparator;

public class PlayerComparator2 {

    public static class gwRank implements Comparator<Player> {
        @Override
        public int compare(Player b1, Player b2) {
            if (b1.live_gw_score > b2.live_gw_score) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
