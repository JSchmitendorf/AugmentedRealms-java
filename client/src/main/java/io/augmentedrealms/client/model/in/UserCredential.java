package io.augmentedrealms.client.model.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCredential {

    @Email(message = "Not a valid email.")
    @NotBlank(message = "Email can't be empty.")
    private String email;

    @NotBlank(message = "Password can't be empty.")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
