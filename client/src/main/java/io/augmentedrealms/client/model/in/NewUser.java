package io.augmentedrealms.client.model.in;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.augmentedrealms.client.model.AbstractUser;
import io.augmentedrealms.common.database.model.Compatible;
import io.augmentedrealms.common.database.model.Player;
import io.augmentedrealms.common.database.model.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUser extends AbstractUser implements Compatible<io.augmentedrealms.common.database.model.User> {

    @NotBlank(message = "Password can't be empty.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @Email(message = "Not a valid email.")
    @NotBlank(message = "Email can't be empty.")
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @NotBlank(message = "Username can't be empty.")
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public io.augmentedrealms.common.database.model.User convertTo() {
        io.augmentedrealms.common.database.model.User user = new io.augmentedrealms.common.database.model.User();
        user.setEmail(getEmail());
        user.setPassword(password);
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setBirthday(getBirthday());
        profile.setUsername(getUsername());
        user.setProfile(profile);
        Player player = new Player();
        player.setUser(user);
        user.setPlayer(player);
        return user;
    }

}
