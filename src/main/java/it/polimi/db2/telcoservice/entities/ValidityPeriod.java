package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "validity_period")
public class ValidityPeriod {
    public ValidityPeriod() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int months;
    @Column(
            nullable = false,
            name = "monthly_fee"
    )
    private BigDecimal monthlyFee;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "val_period_id"
    )
    private Set<ServicePackage> servicePackages;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "opt_prod_id"
    )
    private Set<SubscriptionOrder> subscriptionOrder;

    // GETTERS

    public int getId() {
        return id;
    }

    public int getMonths() {
        return months;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public Set<ServicePackage> getServicePackages() {
        return servicePackages;
    }

    public Set<SubscriptionOrder> getSubscriptionOrder() {
        return subscriptionOrder;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void setServicePackages(Set<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }

    public void setSubscriptionOrder(Set<SubscriptionOrder> subscriptionOrder) {
        this.subscriptionOrder = subscriptionOrder;
    }
}
