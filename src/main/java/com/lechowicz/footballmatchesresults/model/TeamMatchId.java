package com.lechowicz.footballmatchesresults.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamMatchId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer matchId;
    private Integer homeTeamId;
    private Integer awayTeamId;

    public TeamMatchId(){  }

    public TeamMatchId(Integer matchId, Integer homeTeamId, Integer awayTeamId) {
        super();
        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMatchId that = (TeamMatchId) o;
        return Objects.equals(matchId, that.matchId) &&
                Objects.equals(homeTeamId, that.homeTeamId) &&
                Objects.equals(awayTeamId, that.awayTeamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, homeTeamId, awayTeamId);
    }
}
