package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;

@Entity
@Table(name = "team_matches")
public class TeamMatch {
    @EmbeddedId
    private TeamMatchId id = new TeamMatchId();

    @ManyToOne
    @MapsId("matchId")
    private int matchId;

    @ManyToOne
    @MapsId("homeTeamId")
    private Integer homeTeamId;

    @ManyToOne
    @MapsId("awayTeamId")
    private Integer awayTeamId;

    public TeamMatch() { }

    public TeamMatch(int matchId, int homeTeamId, int awayTeamId) {
        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public TeamMatchId getId() {
        return id;
    }

    public void setId(TeamMatchId id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatch(int matchId) {
        this.matchId = matchId;
    }

    public int getHomeTeamId() {
        return this.homeTeamId;
    }

    public void setHomeTeam(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
        return this.awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
