package io.augmentedrealms.client.controller;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.model.in.RealmCredential;
import io.augmentedrealms.client.model.in.NewRealm;
import io.augmentedrealms.client.model.out.Realm;
import io.augmentedrealms.client.service.RealmService;
import io.augmentedrealms.client.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/realm")
public class RealmController {

    private RealmService realmService;

    private UserService userService;

    public RealmController(RealmService realmService, UserService userService) {
        this.realmService = realmService;
        this.userService = userService;
    }

    @PostMapping(path = "/create")
    public Realm createRealm(@Valid @RequestBody NewRealm realm) throws ApiException {
        return Realm.getInstance(realmService.createRealm(realm,userService.getCurrentUserDBModel()));
    }

}
