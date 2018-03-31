package io.augmentedrealms.client.model.out;

public class Player {

    private String username;

    private String email;

    private Player(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Player getInstance(io.augmentedrealms.common.database.model.Player playerDBModel) {
        if(playerDBModel==null){
            return null;
        }
        Player player = new Player();
        player.setUsername(playerDBModel.getUser().getProfile().getUsername());
        player.setEmail(playerDBModel.getUser().getEmail());
        return player;
    }
}
