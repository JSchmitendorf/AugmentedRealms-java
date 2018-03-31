package io.augmentedrealms.client.model.in;


import io.augmentedrealms.common.database.model.Compatible;
import io.augmentedrealms.common.database.model.Realm;

import javax.validation.constraints.NotBlank;

public class NewRealm implements Compatible<io.augmentedrealms.common.database.model.Realm> {

    @NotBlank(message = "Realm name can't be empty.")
    private String name;

    private String password;

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

    @Override
    public io.augmentedrealms.common.database.model.Realm convertTo() {
        Realm realm = new Realm();
        realm.setPassword(password);
        realm.setName(name);
        return realm;
    }
}
