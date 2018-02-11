package io.augmentedrealms.client.model.factory;

import io.augmentedrealms.client.model.PlayerRecord;
import io.augmentedrealms.common.database.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerRecordFactory implements RecordFactory<Player,PlayerRecord> {
    @Override
    public PlayerRecord convertFrom(Player from) {
        if(from==null){
            return null;
        }
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.setUsername(from.getUser().getProfile().getUsername());
        playerRecord.setEmail(from.getUser().getEmail());
        return playerRecord;
    }
}
