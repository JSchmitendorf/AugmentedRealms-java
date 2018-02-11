package io.augmentedrealms.client.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

    private Environment env;

    public VersionController(Environment env) {
        this.env = env;
    }

    @GetMapping()
    public String version() {
        return env.getProperty("artifactId").concat(": ").concat(env.getProperty("version"));
    }
}