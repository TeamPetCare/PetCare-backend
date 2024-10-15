package com.application.petcare.security.model;

import com.application.petcare.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserTokenModel {
    @Id
    private String id;

    private String token;

    private String tokenType = "BEARER";

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected UserTokenModel() {
    }

    public UserTokenModel(String id, String token, String tokenType, User user) {
        this.id = id;
        this.revoked = false;
        this.token = token;
        this.tokenType = tokenType;
        this.expired = false;
        this.user = user;
    }
}
