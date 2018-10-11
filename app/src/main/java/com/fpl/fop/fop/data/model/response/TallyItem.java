package com.fpl.fop.fop.data.model.response;

public class TallyItem {
    public int count, score, id;
    public String name;
    public int whiteWalker, brotherhood, dothraki, valyrians, targaryens, nightsWatch, lannisters, starks, facelessMen, kingslayers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TallyItem tallyItem = (TallyItem) o;

        if (count != tallyItem.count) return false;
        if (score != tallyItem.score) return false;
        if (id != tallyItem.id) return false;
        if (whiteWalker != tallyItem.whiteWalker) return false;
        if (brotherhood != tallyItem.brotherhood) return false;
        if (dothraki != tallyItem.dothraki) return false;
        if (valyrians != tallyItem.valyrians) return false;
        if (targaryens != tallyItem.targaryens) return false;
        if (nightsWatch != tallyItem.nightsWatch) return false;
        if (lannisters != tallyItem.lannisters) return false;
        if (starks != tallyItem.starks) return false;
        if (facelessMen != tallyItem.facelessMen) return false;
        if (kingslayers != tallyItem.kingslayers) return false;
        return name.equals(tallyItem.name);
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + score;
        result = 31 * result + id;
        result = 31 * result + name.hashCode();
        result = 31 * result + whiteWalker;
        result = 31 * result + brotherhood;
        result = 31 * result + dothraki;
        result = 31 * result + valyrians;
        result = 31 * result + targaryens;
        result = 31 * result + nightsWatch;
        result = 31 * result + lannisters;
        result = 31 * result + starks;
        result = 31 * result + facelessMen;
        result = 31 * result + kingslayers;
        return result;
    }
}
