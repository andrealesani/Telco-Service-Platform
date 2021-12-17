package it.polimi.db2.db2jpa2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    public User() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "is_flagged")
    private boolean is_flagged;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIs_flagged() {
        return is_flagged;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_flagged(boolean is_flagged) {
        this.is_flagged = is_flagged;
    }
}
