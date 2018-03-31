package io.augmentedrealms.common.database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player implements DBModel {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "owner",orphanRemoval = true)
    private Set<Realm> ownedRealms;

    public Set<Realm> getOwnedRealms() {
        return ownedRealms;
    }

    public void setOwnedRealms(Set<Realm> ownedRealms) {
        this.ownedRealms = ownedRealms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
