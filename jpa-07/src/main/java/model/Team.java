package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Team {

    @Id
    private String id;
    private String name;
    @OneToMany
    @JoinColumn(name = "team_id")
    private Set<Player> players;

    public Team() {
    }

    public Team(String id, String name, Set<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Player> getPlayers() {
        return players;
    }
}
