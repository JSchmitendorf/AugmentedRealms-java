package io.augmentedrealms.client.model.factory;

import io.augmentedrealms.client.model.RealmRecord;
import io.augmentedrealms.common.database.model.Realm;
import org.springframework.stereotype.Component;

@Component
public class RealmRecordFactory implements RecordFactory<Realm,RealmRecord> {

    private PlayerRecordFactory playerRecordFactory;

    public RealmRecordFactory(PlayerRecordFactory playerRecordFactory) {
        this.playerRecordFactory = playerRecordFactory;
    }

    @Override
    public RealmRecord convertFrom(Realm from) {
        if (from==null){
            return null;
        }
        RealmRecord realmRecord = new RealmRecord();
        realmRecord.setId(from.getId());
        realmRecord.setName(from.getName());
        realmRecord.setOwner(playerRecordFactory.convertFrom(from.getOwner()));
        realmRecord.setParticipants(playerRecordFactory.ConvertSet(from.getParticipants()));
        return realmRecord;
    }
}
