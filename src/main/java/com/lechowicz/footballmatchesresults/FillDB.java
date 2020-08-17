package com.lechowicz.footballmatchesresults;

import com.lechowicz.footballmatchesresults.controller.FootballController;
import com.lechowicz.footballmatchesresults.model.Match;
import com.lechowicz.footballmatchesresults.model.Team;
import com.lechowicz.footballmatchesresults.model.TeamMatch;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FillDB {
    private static SessionFactory factory;

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

        FootballController.insertToDB();

    }
}
