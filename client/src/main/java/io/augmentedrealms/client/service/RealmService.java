package io.augmentedrealms.client.service;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.exception.ApiExceptionResponse;
import io.augmentedrealms.client.model.in.RealmCredential;
import io.augmentedrealms.client.model.in.NewRealm;
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



    public RealmService(PasswordEncoder encoder, RealmRepository realmRepository,
                        UserRepository userRepository) {
        this.encoder = encoder;
        this.realmRepository = realmRepository;
        this.userRepository = userRepository;
    }

    public Realm createRealm(NewRealm realm, User owner){

        if (realm.getPassword() != null && !realm.getPassword().isEmpty()) {
            realm.setPassword(encoder.encode(realm.getPassword()));
        }
        Realm newRealm = realm.convertTo();
        newRealm.setOwner(owner.getPlayer());
        owner.getPlayer().getOwnedRealms().add(newRealm);
        userRepository.save(owner);
        realmRepository.save(newRealm);
        return newRealm;
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
