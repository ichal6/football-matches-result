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
    home_team_id INT,
    away_team_id INT,
    goals_home INT,
    goals_away INT,
    PRIMARY KEY(id)
);

CREATE TABLE team_matches(
    team_id INT NOT NULL,
    matches_id INT NOT NULL,
    PRIMARY KEY(team_id, matches_id)
);