package io.augmentedrealms.client.model.in;

import javax.validation.constraints.NotNull;

public class RealmCredential {

    @NotNull(message = "Realm id can't be null.")
    private Long id;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
