package io.augmentedrealms.client.model.factory;

import io.augmentedrealms.client.model.UserDetailRecord;
import io.augmentedrealms.common.database.model.Realm;
import io.augmentedrealms.common.database.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailRecordFactory implements RecordFactory<User,UserDetailRecord> {



    @Override
    public UserDetailRecord convertFrom(User from) {
        if(from==null){
            return null;
        }
        UserDetailRecord userDetailRecord = new UserDetailRecord();
        userDetailRecord.setEmail(from.getEmail());
        userDetailRecord.setBirthday(from.getProfile().getBirthday());
        userDetailRecord.setUsername(from.getProfile().getUsername());
        if (from.getPlayer().getCurRealm()!=null){
            userDetailRecord.setCurRealmId(from.getPlayer().getCurRealm().getId());
        }
        userDetailRecord.setOwnedRealmIds(getRealmIdSet(from.getPlayer().getOwnedRealms()));
        return userDetailRecord;
    }

    private Set<Long> getRealmIdSet(Set<Realm> realmSet) {
        if (realmSet==null){
            return null;
        }
        Set<Long> idSet = new HashSet<>();
        for (Realm realm : realmSet) {
            idSet.add(realm.getId());
        }
        return idSet;
    }
}
