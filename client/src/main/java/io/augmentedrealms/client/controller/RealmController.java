package io.augmentedrealms.client.controller;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.model.RealmCredential;
import io.augmentedrealms.client.model.RealmRecord;
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
    public RealmRecord createRealm(@Valid @RequestBody RealmRecord realm) throws ApiException {
        return realmService.createRealm(realm,userService.getUserFromHeader());
    }

    @PostMapping(path = "/join")
    public RealmRecord joinRealm(@Valid @RequestBody RealmCredential realmCredential) throws ApiException {
        return realmService.joinRealm(realmCredential,userService.getUserFromHeader());
    }

    @GetMapping
    public RealmRecord realmSync() throws ApiException {
        return realmService.realmSync(userService.getUserFromHeader());
    }

    @GetMapping(path = "/leave")
    @ResponseStatus(value = HttpStatus.OK)
    public void leaveRealm() throws ApiException {
        realmService.leaveRealm(userService.getUserFromHeader());
    }
}
