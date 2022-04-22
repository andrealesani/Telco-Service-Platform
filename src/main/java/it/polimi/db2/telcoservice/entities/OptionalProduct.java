package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "optional_product")
@NamedQueries({
        @NamedQuery(name = "OptionalProduct.findAllOptionalProducts", query = "SELECT op FROM OptionalProduct op"),
        @NamedQuery(name = "OptionalProduct.findNumOptionalProducts", query = "SELECT count(op) FROM OptionalProduct op"),
})
public class OptionalProduct {
    public OptionalProduct() {
    }

    public OptionalProduct(String name, BigDecimal monthlyFee) {
        this.name = name;
        this.monthlyFee = monthlyFee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(
            nullable = false,
            name = "monthly_fee"
    )
    private BigDecimal monthlyFee;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "optionalProducts"
    )
    private Set<ServicePackage> servicePackages;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "optionalProducts"
    )
    private Set<SubscriptionOrder> subscriptionOrder;

    // GETTERS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
