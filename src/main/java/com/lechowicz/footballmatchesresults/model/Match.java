package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "match")
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
    private int allGoals;

    public Match(){ }

    public Match(Date date, int goalsHome, int goalsAway) {
        this.date = date;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
        this.allGoals = goalsAway + goalsHome;
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
        this.allGoals  = goalsHome + this.goalsAway;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
        this.allGoals = goalsAway + this.goalsHome;
    }

    public int getAllGoals() {
        return allGoals;
    }
}
