package io.augmentedrealms.client.model.out;

import io.augmentedrealms.client.model.AbstractUser;
import io.augmentedrealms.client.model.Util;

import java.util.Set;

public class User extends AbstractUser {

    private Set<Long> ownedRealmIds;

    private Long curRealmId;

    private User(){};

    public Set<Long> getOwnedRealmIds() {
        return ownedRealmIds;
    }

    public void setOwnedRealmIds(Set<Long> ownedRealmIds) {
        this.ownedRealmIds = ownedRealmIds;
    }

    public Long getCurRealmId() {
        return curRealmId;
    }

    public void setCurRealmId(Long curRealmId) {
        this.curRealmId = curRealmId;
    }

    public static User getInstance(io.augmentedrealms.common.database.model.User userDBModel) {
        if(userDBModel==null){
            return null;
        }
        User user = new User();
        user.setEmail(userDBModel.getEmail());
        user.setBirthday(userDBModel.getProfile().getBirthday());
        user.setUsername(userDBModel.getProfile().getUsername());
        user.setOwnedRealmIds(Util.getInstanceMap(userDBModel.getPlayer().getOwnedRealms(),
                model -> Realm.getInstance(model)).keySet());
        return user;
    }


}
