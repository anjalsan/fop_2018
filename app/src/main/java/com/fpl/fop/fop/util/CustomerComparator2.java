package com.fpl.fop.fop.util;

import com.fpl.fop.fop.data.model.response.BfwTeam;

import java.util.Comparator;

public class CustomerComparator2 {

    public static class gwRank implements Comparator<BfwTeam> {
        @Override
        public int compare(BfwTeam b1, BfwTeam b2) {
            if (b1.game_week != null && b2.game_week != null) {
                if (b1.game_week.rank > b2.game_week.rank) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        }
    }

    public static class totalPoint implements Comparator<BfwTeam> {
        @Override
        public int compare(BfwTeam b1, BfwTeam b2) {
            if (b1.total_points > b2.total_points) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
