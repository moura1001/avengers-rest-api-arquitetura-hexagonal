package com.moura.avengersapi.domain.avenger;

public class Avenger {
    private Long id;
    private String nick;
    private String person;
    private String description;
    private String history;

    public Avenger(String nick, String person, String description, String history) {
        this.nick = nick;
        this.person = person;
        this.description = description;
        this.history = history;
    }

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getPerson() {
        return person;
    }

    public String getDescription() {
        return description;
    }

    public String getHistory() {
        return history;
    }
}
