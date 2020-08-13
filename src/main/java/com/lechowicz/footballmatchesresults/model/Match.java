package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    @Column(name = "goals_home")
    private int goalsHome;
    @Column(name = "goals_away")
    private int goalsAway;
    @Transient
    private Team teamHome;
    @Transient
    private Team teamAway;

    public Match(){ }

    public Match(Date date, int goalsHome, int goalsAway) {
        this.date = date;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
