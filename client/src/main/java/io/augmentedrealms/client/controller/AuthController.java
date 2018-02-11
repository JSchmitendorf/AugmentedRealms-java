package io.augmentedrealms.client.controller;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.model.UserCredential;
import io.augmentedrealms.client.model.UserRecord;
import io.augmentedrealms.client.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@Valid @RequestBody UserRecord newUser) throws ApiException {
        userService.userRegister(newUser);
    }

    @PostMapping(path = "/login")
    public String login(@Valid @RequestBody UserCredential userCredential) throws ApiException {
        return userService.userLogin(userCredential);

    }
}
