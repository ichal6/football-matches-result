create table TEAM (
    id INT NOT NULL ,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
);

create table matches(
    id INT NOT NULL,
    date DATE NOT NULL,
    home_team_id INT NOT NULL,
    away_team_id INT NOT NULL,
    goals_home INT,
    goals_away INT
);
