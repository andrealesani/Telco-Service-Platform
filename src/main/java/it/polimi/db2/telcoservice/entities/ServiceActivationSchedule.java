package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "service_activation_schedule")
public class ServiceActivationSchedule {
    public ServiceActivationSchedule() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "activation_date")
    private Timestamp activationDate;
    @Column(name = "deactivation_date")
    private Timestamp deactivationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Timestamp activationDate) {
        this.activationDate = activationDate;
    }

    public Timestamp getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Timestamp deactivationDate) {
        this.deactivationDate = deactivationDate;
    }
}
