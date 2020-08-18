package com.lechowicz.footballmatchesresults.controller;

import com.lechowicz.footballmatchesresults.model.Match;
import com.lechowicz.footballmatchesresults.model.Team;
import com.lechowicz.footballmatchesresults.model.TeamMatch;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class FootballController {
    private static SessionFactory factory;
    private static FootballController footballController;

    public static void main(String[] args) {
        try {
            factory = new Configuration().
                    configure().
                    addAnnotatedClass(Team.class).
                    addAnnotatedClass(Match.class).
                    addAnnotatedClass(TeamMatch.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        footballController = new FootballController();

        displayMatchesAndTeams();
        displayTeamsWithFC();
        displayMatchWithTheMostGoals();
    }

    private List<Team> getTeamsWithFC(){
         return getTeams()
                .stream()
                .filter(team -> team.getName().contains("FC"))
                .collect(Collectors.toList());

    }

    private TeamMatch getMatchWithTheMostGoals(){
        Optional<TeamMatch> teamMatchOptional = getMatches()
                .stream()
                .reduce((match1, match2)
                        -> (match1.getMatch().getAllGoals()) > (match2.getMatch().getAllGoals())
                        ? match1 : match2);
        return teamMatchOptional.orElse(null);
    }

    private void displayTeams(List<Team> teams){
        teams.forEach(System.out::println);
    }

    private void displayTeamMatch(TeamMatch teamMatch){
        System.out.println(teamMatch);
    }

    private static void displayMatchWithTheMostGoals(){
        footballController.displayTeamMatch(footballController.getMatchWithTheMostGoals());
    }

    private static void displayTeamsWithFC(){
        footballController.displayTeams(footballController.getTeamsWithFC());
    }

    private static void displayMatchesAndTeams(){
        List<TeamMatch> teamMatches = footballController.getMatches();
        List<Team> teams = footballController.getTeams();

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

    public static void insertToDB(){
        FootballController controller = new FootballController();
        Map<Long, Team> teams = new HashMap<>();

        Team newTeam = controller.addTeam("FC-zlewce", "Poland", "Zalesie");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("Stare-gacie", "Poland", "Buda Wielka");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("AlJazira", "Egypt", "Kair");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("Kowboy", "USA", "Texas");
        teams.put(newTeam.getId(), newTeam);

        controller.addMatch(new Date(855765L), teams.get(1L),  teams.get(2L), 2,3);
        controller.addMatch(new Date(54365435L), teams.get(3L), teams.get(4L), 0, 4);
        controller.addMatch(new Date(35666), teams.get(4L), teams.get(3L), 5, 2);
    }

    public List<TeamMatch> getMatches(){
        Session session = factory.openSession();
        List<TeamMatch> teamMatches = null;

        try{
            teamMatches = session.createQuery("FROM TeamMatch").list();
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return teamMatches;
    }

    public List<Team> getTeams(){
        Session session = factory.openSession();
        List<Team> teams = null;
        try{
            teams = session.createQuery("FROM Team").list();
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

        return teams;
    }

    private Team addTeam(String name, String country, String city){
        Session session = factory.openSession();
        Transaction tx = null;
        Team team = null;

        try {
            tx = session.beginTransaction();
            team = new Team(name, country, city);
            long teamID = (Long) session.save(team);
            team.setId(teamID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return team;
    }

    private Match addMatch(Date date, Team homeTeam, Team awayTeam, int goalsHome, int goalsAway){
        Session session = factory.openSession();
        Transaction tx = null;
        Match match = null;

        try {
            tx = session.beginTransaction();
            match = new Match(date, goalsHome, goalsAway);
            long matchID = (Long) session.save(match);
            match.setId(matchID);
            TeamMatch teamMatch = new TeamMatch(match, homeTeam, awayTeam);
            session.save(teamMatch);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return match;
    }
}
