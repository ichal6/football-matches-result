package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamMatchId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long matchId;
    private Long homeTeamId;
    private Long awayTeamId;

    public TeamMatchId(){  }

    public TeamMatchId(Long matchId, Long homeTeamId, Long awayTeamId) {
        super();
        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }


    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }


    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }


    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
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
