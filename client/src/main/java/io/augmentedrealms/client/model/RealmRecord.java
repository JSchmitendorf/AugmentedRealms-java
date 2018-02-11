package io.augmentedrealms.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.augmentedrealms.common.database.model.Compatible;
import io.augmentedrealms.common.database.model.Realm;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class RealmRecord implements Compatible<Realm> {

    private Long id;

    @NotBlank(message = "Realm name can't be empty.")
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private PlayerRecord owner;

    private Set<PlayerRecord> participants;

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerRecord getOwner() {
        return owner;
    }

    public void setOwner(PlayerRecord owner) {
        this.owner = owner;
    }

    public Set<PlayerRecord> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<PlayerRecord> participants) {
        this.participants = participants;
    }

    @Override
    public Realm convertTo() {
        Realm realm = new Realm();
        realm.setPassword(password);
        realm.setName(name);
        return realm;
    }

}
