package io.augmentedrealms.common.database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "realm")
public class Realm  implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    @ManyToOne
    private Player owner;

    @OneToMany(mappedBy = "curRealm")
    private Set<Player> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Set<Player> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Player> participants) {
        this.participants = participants;
    }


}

