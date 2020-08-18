package com.lechowicz.footballmatchesresults.model;

import javax.persistence.*;


@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String country;
    private String city;

    public Team(){ }

    public Team(String name, String country, String city) {
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Team number " + getId() + "\n" +
        "name: " + getName() + "\n" +
        "country: " + getCountry() + "\n" +
        "city: " + getCity() + "\n";
    }
}
