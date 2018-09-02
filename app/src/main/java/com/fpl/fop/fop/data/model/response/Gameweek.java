package com.fpl.fop.fop.data.model.response;


import java.io.Serializable;

public class Gameweek implements Serializable {
    public Player captain;
    public Player substitute;
    public CurrentGw game_week;
    public TeamPoint team;
    public int total_score, points, rank;
    public boolean bonus_gained, penalty_applied;
}
