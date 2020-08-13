package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;

@Entity
@Table(name = "team_match")
public class TeamMatch {
    @EmbeddedId
    private TeamMatchId id = new TeamMatchId();

    @ManyToOne
    @MapsId("matchId")
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @MapsId("homeTeamId")
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @MapsId("awayTeamId")
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    public TeamMatch() { }

    public TeamMatch( Match match, Team homeTeam, Team awayTeam) {
        this.match = match;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.id.setMatchId(match.getId());
        this.id.setHomeTeamId(homeTeam.getId());
        this.id.setAwayTeamId(awayTeam.getId());
    }

    public TeamMatchId getId() {
        return id;
    }

    public void setId(TeamMatchId id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
}
