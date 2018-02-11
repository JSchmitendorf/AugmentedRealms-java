package io.augmentedrealms.common.database.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profile")
public class Profile implements Model {

    @Id
    private Long id;

    private Date birthday;

    @OneToOne
    @MapsId
    private User user;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

