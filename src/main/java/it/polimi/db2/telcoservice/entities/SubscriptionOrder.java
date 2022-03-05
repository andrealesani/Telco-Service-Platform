package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SubscriptionOrder {
    public SubscriptionOrder() {
    }

    @Id
    @GeneratedValue
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
