package com.fpl.fop.fop.data.model.response;

public class TallyItem {
    public float count;
    public int score, id;
    public String name;
    public float whiteWalker, brotherhood, dothraki, valyrians, targaryens, nightsWatch, lannisters, starks, facelessMen, kingslayers;

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
        int result = (count != +0.0f ? Float.floatToIntBits(count) : 0);
        result = 31 * result + score;
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (whiteWalker != +0.0f ? Float.floatToIntBits(whiteWalker) : 0);
        result = 31 * result + (brotherhood != +0.0f ? Float.floatToIntBits(brotherhood) : 0);
        result = 31 * result + (dothraki != +0.0f ? Float.floatToIntBits(dothraki) : 0);
        result = 31 * result + (valyrians != +0.0f ? Float.floatToIntBits(valyrians) : 0);
        result = 31 * result + (targaryens != +0.0f ? Float.floatToIntBits(targaryens) : 0);
        result = 31 * result + (nightsWatch != +0.0f ? Float.floatToIntBits(nightsWatch) : 0);
        result = 31 * result + (lannisters != +0.0f ? Float.floatToIntBits(lannisters) : 0);
        result = 31 * result + (starks != +0.0f ? Float.floatToIntBits(starks) : 0);
        result = 31 * result + (facelessMen != +0.0f ? Float.floatToIntBits(facelessMen) : 0);
        result = 31 * result + (kingslayers != +0.0f ? Float.floatToIntBits(kingslayers) : 0);
        return result;
    }
}
