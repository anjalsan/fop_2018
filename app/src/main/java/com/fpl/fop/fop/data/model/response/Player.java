package com.fpl.fop.fop.data.model.response;

import java.io.Serializable;

public class Player implements Serializable {
    public String id, first_name, last_name;
    public long fpl_id, live_gw_score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (fpl_id != player.fpl_id) return false;
        if (live_gw_score != player.live_gw_score) return false;
        if (!id.equals(player.id)) return false;
        if (first_name != null ? !first_name.equals(player.first_name) : player.first_name != null)
            return false;
        return last_name != null ? last_name.equals(player.last_name) : player.last_name == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (int) (fpl_id ^ (fpl_id >>> 32));
        result = 31 * result + (int) (live_gw_score ^ (live_gw_score >>> 32));
        return result;
    }
}
