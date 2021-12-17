package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Auditing {
    public Auditing() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Timestamp ts_rejection;
    @Column
    private String userID;

    public Long getId() {
        return id;
    }

    public Timestamp getTs_rejection() {
        return ts_rejection;
    }

    public String getUserID() {
        return userID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTs_rejection(Timestamp ts_rejection) {
        this.ts_rejection = ts_rejection;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
