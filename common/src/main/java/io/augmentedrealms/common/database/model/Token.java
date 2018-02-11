package io.augmentedrealms.common.database.model;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tokenName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Token(String tokenName) {
        this.tokenName = tokenName;
    }

    public Token() { }
}
