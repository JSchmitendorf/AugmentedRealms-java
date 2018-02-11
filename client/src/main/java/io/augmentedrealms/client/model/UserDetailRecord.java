package io.augmentedrealms.client.model;

import java.util.Set;

public class UserDetailRecord extends UserRecord {

    private Set<Long> ownedRealmIds;

    private Long curRealmId;

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

}
