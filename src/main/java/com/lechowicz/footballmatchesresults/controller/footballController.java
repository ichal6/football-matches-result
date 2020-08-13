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
import java.util.HashMap;
import java.util.Map;

public class footballController {
    private static SessionFactory factory;

    public static void main(String[] args) {
        footballController controller = new footballController();
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
        Map<Long, Team> teams = new HashMap<>();
        Team newTeam = controller.addTeam("FC-zlewce", "Poland", "Zalesie");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("Stare-gacie", "Poland", "Buda Wielka");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("AlJazira", "Egypt", "Kair");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("Kowboy", "USA", "Texas");
        teams.put(newTeam.getId(), newTeam);

        controller.addMatch(new Date(432432L), teams.get(1L),  teams.get(2L), 2,3, teams);

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

    private long addMatch(Date date, Team homeTeam, Team awayTeam, int goalsHome, int goalsAway, Map<Long, Team> teams){
        Session session = factory.openSession();
        Transaction tx = null;
        Long matchID = null;

        try {
            tx = session.beginTransaction();
            Match match = new Match(date, goalsHome, goalsAway);
            match.setTeamAway(awayTeam);
            match.setTeamHome(homeTeam);
            matchID = (Long) session.save(match);
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
        return matchID;
    }
}
