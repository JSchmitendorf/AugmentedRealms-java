package io.augmentedrealms.client.controller;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.model.UserRecord;
import io.augmentedrealms.client.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UserRecord getUserDetail() throws ApiException {
        return userService.getUserDetail(userService.getUserFromHeader());
    }

    @GetMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public void logOut() throws ApiException {
        userService.userLogOut(userService.getUserFromHeader());
    }
}
