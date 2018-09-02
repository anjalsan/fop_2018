package com.fpl.fop.fop.data.model.response;

public class TallyItem {
    public int count, score, id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TallyItem tallyItem = (TallyItem) o;

        if (count != tallyItem.count) return false;
        if (score != tallyItem.score) return false;
        return name.equals(tallyItem.name);
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + score;
        result = 31 * result + name.hashCode();
        return result;
    }
}
