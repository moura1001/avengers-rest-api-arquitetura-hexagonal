package com.moura.avengersapi.infra;

import com.moura.avengersapi.domain.avenger.Avenger;
import jakarta.persistence.*;

@Entity
@Table(name = "avenger")
public class AvengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nick;
    private String person;
    private String description;
    private String history;

    public AvengerEntity() {}

    public AvengerEntity(Long id, String nick, String person, String description, String history) {
        this.id = id;
        this.nick = nick;
        this.person = person;
        this.description = description;
        this.history = history;
    }

    public AvengerEntity(String nick, String person, String description, String history) {
        this.nick = nick;
        this.person = person;
        this.description = description;
        this.history = history;
    }

    public Avenger toDomain() {
        return new Avenger(
                this.id,
                this.nick,
                this.person,
                this.description,
                this.history
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
