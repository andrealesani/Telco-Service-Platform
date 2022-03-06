package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "subscription_order")
public class SubscriptionOrder {
    public SubscriptionOrder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(
            nullable = false,
            name = "total_value"
    )
    private BigDecimal totalValue;
    @Column(
            nullable = false,
            name = "creation_ts"
    )
    private Timestamp creationTs;
    @Column(
            nullable = false,
            name = "start_date_ts"
    )
    private Timestamp startDateTs;
    @Column(
            nullable = false,
            name = "deactivation_date_ts"
    )
    private Timestamp deactivationDateTs;
    @Column(nullable = false)
    private boolean valid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "serv_pckg_id"
    )
    private ServicePackage servicePackage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "val_period_id"
    )
    private ValidityPeriod validityPeriod;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "sub_order_id"),
            inverseJoinColumns = @JoinColumn(name = "opt_prod_id")
    )
    private Set<OptionalProduct> optionalProducts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    // GETTER

    public int getId() {
        return id;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Timestamp getCreationTs() {
        return creationTs;
    }

    public Timestamp getStartDateTs() {
        return startDateTs;
    }

    public Timestamp getDeactivationDateTs() {
        return deactivationDateTs;
    }

    public boolean isValid() {
        return valid;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public Set<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public User getUser() {
        return user;
    }

    // SETTER

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void setCreationTs(Timestamp creationTs) {
        this.creationTs = creationTs;
    }

    public void setStartDateTs(Timestamp startDateTs) {
        this.startDateTs = startDateTs;
    }

    public void setDeactivationDateTs(Timestamp deactivationDateTs) {
        this.deactivationDateTs = deactivationDateTs;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public void setOptionalProducts(Set<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
