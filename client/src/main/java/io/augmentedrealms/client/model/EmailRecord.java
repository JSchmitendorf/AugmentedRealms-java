package io.augmentedrealms.client.model;

import io.augmentedrealms.common.database.model.Compatible;
import io.augmentedrealms.common.database.model.EmailListItem;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailRecord implements Compatible<EmailListItem> {

    @Email(message = "Not a valid email.")
    @NotBlank(message = "Email can't be empty.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public EmailListItem convertTo() {
        EmailListItem emailListItem = new EmailListItem();
        emailListItem.setEmail(email);
        return emailListItem;
    }

}
