package it.polimi.db2.telcoservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Order {
    public Order() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Timestamp creation_ts;
    @Column(name = "is_valid")
    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public Timestamp getCreation_ts() {
        return creation_ts;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreation_ts(Timestamp creation_ts) {
        this.creation_ts = creation_ts;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
