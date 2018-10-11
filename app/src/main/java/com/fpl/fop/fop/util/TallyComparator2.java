package com.fpl.fop.fop.util;

import com.fpl.fop.fop.data.model.response.TallyItem;

import java.util.Comparator;

public class TallyComparator2 {

    public static class gwRank implements Comparator<TallyItem> {
        @Override
        public int compare(TallyItem b1, TallyItem b2) {
            if (b1.count > b2.count) {
                return -1;
            } else if (b1.count < b2.count) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
