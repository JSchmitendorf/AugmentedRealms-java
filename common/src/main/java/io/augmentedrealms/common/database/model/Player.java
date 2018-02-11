package io.augmentedrealms.common.database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player implements Model {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "owner",orphanRemoval = true)
    private Set<Realm> ownedRealms;

    @ManyToOne
    @JoinColumn()
    private Realm curRealm;

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

    public Realm getCurRealm() {
        return curRealm;
    }

    public void setCurRealm(Realm curRealm) {
        this.curRealm = curRealm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
