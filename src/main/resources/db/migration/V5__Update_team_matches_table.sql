DROP TABLE IF EXISTS TEAM;
DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS team_matches;

create table TEAM (
    id SERIAL NOT NULL,
    name VARCHAR(100),
    country VARCHAR(100),
    city VARCHAR(100),
    PRIMARY KEY(id)
);

create table matches(
    id SERIAL NOT NULL,
    date DATE,
    goals_home INT,
    goals_away INT,
    PRIMARY KEY(id)
);

CREATE TABLE team_matches(
    matches_id INT NOT NULL,
    home_team_id INT NOT NULL ,
    away_team_id INT NOT NULL ,
    PRIMARY KEY(home_team_id, away_team_id, matches_id)
);