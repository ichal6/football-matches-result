package com.lechowicz.footballmatchesresults.controller;

import com.lechowicz.footballmatchesresults.model.Match;
import com.lechowicz.footballmatchesresults.model.Team;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class footballController {
    private static SessionFactory factory;

    public static void main(String[] args) {
        footballController controller = new footballController();
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Map<Integer, Team> teams = new HashMap<>();
        Team newTeam = controller.addTeam("FC-zlewce", "Poland", "Zalesie");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("Stare-gacie", "Poland", "Buda Wielka");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("AlJazira", "Egypt", "Kair");
        teams.put(newTeam.getId(), newTeam);
        newTeam = controller.addTeam("Kowboy", "USA", "Texas");
        teams.put(newTeam.getId(), newTeam);

        controller.addMatch(new Date(432432L), teams.get(1),  teams.get(2), 2,3, teams);

    }

    private Team addTeam(String name, String country, String city){
        Session session = factory.openSession();
        Transaction tx = null;
        Team team = null;

        try {
            tx = session.beginTransaction();
            team = new Team(name, country, city);
            int teamID = (Integer) session.save(team);
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

    private int addMatch(Date date, Team homeTeam, Team awayTeam, int goalsHome, int goalsAway, Map<Integer, Team> teams){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer matchID = null;

        try {
            tx = session.beginTransaction();
            Match match = new Match(date, goalsHome, goalsAway);
            match.setTeamAway(awayTeam);
            match.setTeamHome(homeTeam);
            matchID = (Integer) session.save(match);
            fillCommonTable(session, matchID, homeTeam, awayTeam);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return matchID;
    }

    private void fillCommonTable(Session session, int matchId, Team homeTeam, Team awayTeam){
        session.createNativeQuery("INSERT INTO team_matches (matches_id, home_team_id, away_team_id) VALUES (?,?,?)")
                .setParameter(1, matchId)
                .setParameter(2, homeTeam.getId())
                .setParameter(3, awayTeam.getId())
                .executeUpdate();
    }
}
