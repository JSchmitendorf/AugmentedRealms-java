package io.augmentedrealms.client.controller;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.model.EmailRecord;
import io.augmentedrealms.client.service.EmailListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/email")
public class EmailListController {

    private EmailListService emailListService;

    public EmailListController(EmailListService emailListService) {
        this.emailListService = emailListService;
    }

    @PostMapping
    public void addEmailToList(@RequestBody @Valid EmailRecord emailRecord) throws ApiException{
        emailListService.addToEmailList(emailRecord);
    }
}
