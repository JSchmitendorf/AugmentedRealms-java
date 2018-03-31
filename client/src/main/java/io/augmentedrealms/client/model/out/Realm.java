package io.augmentedrealms.client.model.out;
import io.augmentedrealms.client.model.Util;

import java.util.ArrayList;
import java.util.List;

public class Realm {

    private String name;

    private Player owner;

    private Realm(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public static Realm getInstance(io.augmentedrealms.common.database.model.Realm realmDBModel) {
        if (realmDBModel == null) {
            return null;
        }
        Realm realm = new Realm();
        realm.setName(realmDBModel.getName());
        realm.setOwner(Player.getInstance(realmDBModel.getOwner()));
        return realm;
    }


}
