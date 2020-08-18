package com.lechowicz.footballmatchesresults.controller;

import com.lechowicz.footballmatchesresults.model.Match;
import com.lechowicz.footballmatchesresults.model.Team;
import com.lechowicz.footballmatchesresults.model.TeamMatch;
import com.lechowicz.footballmatchesresults.service.FootballService;

import java.util.*;
import java.util.stream.Collectors;

public class FootballController {
    private static FootballService footballService;

    public static void main(String[] args) {
        footballService = new FootballService();

        displayMatchesAndTeams();
        displayTeamsWithFC();
        displayMatchWithTheMostGoals();
    }

    private static void displayTeams(List<Team> teams){
        teams.forEach(System.out::println);
    }

    private static void displayTeamMatch(TeamMatch teamMatch){
        System.out.println(teamMatch);
    }

    private static void displayMatchWithTheMostGoals(){
        displayTeamMatch(footballService.getMatchWithTheMostGoals());
    }

    private static void displayTeamsWithFC(){
        displayTeams(footballService.getTeamsWithFC());
    }

    private static void displayMatchesAndTeams(){
        List<TeamMatch> teamMatches = footballService.getMatches();
        List<Team> teams = footballService.getTeams();

        for (Iterator iterator2 = teamMatches.iterator() ; iterator2.hasNext();){
            TeamMatch teamMatch = (TeamMatch) iterator2.next();
            Match match = teamMatch.getMatch();
            System.out.println("Match number " + match.getId());
            System.out.println("Date: " + match.getDate());
            System.out.println("Goals for home: " + match.getGoalsHome());
            System.out.println("Goals for away:" + match.getGoalsAway());
            Team teamAway = teamMatch.getAwayTeam();
            Team teamHome = teamMatch.getHomeTeam();
            System.out.println("Team home: " + teamHome.getName());
            System.out.println("Team away: " + teamAway.getName());
            System.out.println();
        }

        for(Team team: teams){
            System.out.println("Team number " + team.getId());
            System.out.println("name: " + team.getName());
            System.out.println("country: " + team.getCountry());
            System.out.println("city: " + team.getCity());
            System.out.println();
        }
    }

}
