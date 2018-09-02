package com.fpl.fop.fop.data.model.response;

import java.io.Serializable;
import java.util.ArrayList;

public class BfwTeam implements Serializable {

    public String name, image_url, updated_at;
    public int id;
    public int total_points;
    public int no_of_wins;
    public int no_of_bonus;
    public Player manager;
    public ArrayList<Player> players;
    public ArrayList<Chip> chips;
    public Gameweek game_week;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTotal_points() {
        return total_points;
    }

    public int getNo_of_wins() {
        return no_of_wins;
    }

    public int getNo_of_bonus() {
        return no_of_bonus;
    }

    public Player getManager() {
        return manager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Chip> getChips() {
        return chips;
    }

    public Gameweek getGame_week() {
        return game_week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BfwTeam bfwTeam = (BfwTeam) o;

        if (id != bfwTeam.id) return false;
        if (total_points != bfwTeam.total_points) return false;
        if (no_of_wins != bfwTeam.no_of_wins) return false;
        if (no_of_bonus != bfwTeam.no_of_bonus) return false;
        if (!name.equals(bfwTeam.name)) return false;
        if (manager != null ? !manager.equals(bfwTeam.manager) : bfwTeam.manager != null)
            return false;
        if (players != null ? !players.equals(bfwTeam.players) : bfwTeam.players != null)
            return false;
        if (chips != null ? !chips.equals(bfwTeam.chips) : bfwTeam.chips != null) return false;
        return game_week != null ? game_week.equals(bfwTeam.game_week) : bfwTeam.game_week == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        result = 31 * result + total_points;
        result = 31 * result + no_of_wins;
        result = 31 * result + no_of_bonus;
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (players != null ? players.hashCode() : 0);
        result = 31 * result + (chips != null ? chips.hashCode() : 0);
        result = 31 * result + (game_week != null ? game_week.hashCode() : 0);
        return result;
    }
}
