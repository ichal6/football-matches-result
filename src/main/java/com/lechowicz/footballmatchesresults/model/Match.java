package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;
import java.util.Set;

public class Match {
    private int id;
    private Date date;
    private int goalsHome;
    private int goalsAway;
    private Team teamHome;
    private Team teamAway;

    public Match(){ }

    public Match(Date date, int goalsHome, int goalsAway) {
        this.date = date;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }
}
