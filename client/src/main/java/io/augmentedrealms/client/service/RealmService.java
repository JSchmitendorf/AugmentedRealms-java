package io.augmentedrealms.client.service;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.exception.ApiExceptionResponse;
import io.augmentedrealms.client.model.RealmCredential;
import io.augmentedrealms.client.model.RealmRecord;
import io.augmentedrealms.client.model.factory.RealmRecordFactory;
import io.augmentedrealms.common.database.model.Realm;
import io.augmentedrealms.common.database.model.User;
import io.augmentedrealms.common.database.repository.RealmRepository;
import io.augmentedrealms.common.database.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealmService {

    private PasswordEncoder encoder;

    private RealmRepository realmRepository;

    private UserRepository userRepository;

    private RealmRecordFactory realmRecordFactory;


    public RealmService(PasswordEncoder encoder, RealmRepository realmRepository,
                        UserRepository userRepository, RealmRecordFactory realmRecordFactory) {
        this.encoder = encoder;
        this.realmRepository = realmRepository;
        this.userRepository = userRepository;
        this.realmRecordFactory = realmRecordFactory;
    }

    public RealmRecord createRealm(RealmRecord realmRecord,User owner){

        if (realmRecord.getPassword() != null && !realmRecord.getPassword().isEmpty()) {
            realmRecord.setPassword(encoder.encode(realmRecord.getPassword()));
        }
        Realm realm = realmRecord.convertTo();
        realm.setOwner(owner.getPlayer());
        owner.getPlayer().getOwnedRealms().add(realm);
        userRepository.save(owner);
        realmRepository.save(realm);
        return realmRecordFactory.convertFrom(realm);
    }

    public RealmRecord joinRealm(RealmCredential realmCredential,User user) throws ApiException {
        Realm realm = getRealmFromRepo(realmCredential.getId());
        if (realm.getPassword()!=null && !encoder.matches(realmCredential.getPassword(),realm.getPassword())) {
            throw new ApiException(ApiExceptionResponse.PASSWORD_INCORRECT);
        }
        realm.getParticipants().add(user.getPlayer());
        user.getPlayer().setCurRealm(realm);
        realmRepository.save(realm);
        userRepository.save(user);
        return realmRecordFactory.convertFrom(realm);
    }

    public RealmRecord realmSync(User user) throws ApiException {
        Realm curRealm = user.getPlayer().getCurRealm();
        if(curRealm == null ){
            throw new ApiException(ApiExceptionResponse.REALM_NOT_FOUND);
        }
        return realmRecordFactory.convertFrom(user.getPlayer().getCurRealm());
    }

    public void leaveRealm(User user) {
        Realm realm = user.getPlayer().getCurRealm();
        realm.getParticipants().remove(user.getPlayer());
        user.getPlayer().setCurRealm(null);
        realmRepository.save(realm);
        userRepository.save(user);

    }

    @NotNull
    private Realm getRealmFromRepo(Long id) throws ApiException{
        Optional<Realm> realm = realmRepository.findById(id);
        if (!realm.isPresent()) {
            throw new ApiException(ApiExceptionResponse.REALM_NOT_FOUND);
        }
        return realm.get();
    }



}
