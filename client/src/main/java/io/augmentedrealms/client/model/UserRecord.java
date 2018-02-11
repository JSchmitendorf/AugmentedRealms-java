package io.augmentedrealms.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.augmentedrealms.common.database.model.Compatible;
import io.augmentedrealms.common.database.model.Player;
import io.augmentedrealms.common.database.model.Profile;
import io.augmentedrealms.common.database.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class UserRecord implements Compatible<User> {

    @Email(message = "Not a valid email.")
    @NotBlank(message = "Email can't be empty.")
    private String email;

    @NotBlank(message = "Password can't be empty.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Username can't be empty.")
    private String username;

    private Date birthday;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public User convertTo() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setBirthday(birthday);
        profile.setUsername(username);
        user.setProfile(profile);

        Player player = new Player();
        player.setUser(user);
        user.setPlayer(player);
        return user;
    }

}
