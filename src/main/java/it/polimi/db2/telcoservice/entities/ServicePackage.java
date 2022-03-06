package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service_package")
@NamedQueries({
        @NamedQuery(name = "ServicePackage.findAllServicePackages", query = "SELECT sp FROM ServicePackage sp"),
        @NamedQuery(name = "ServicePackage.findServicePackageById", query = "SELECT sp FROM ServicePackage sp where sp.id=?1")

})
public class ServicePackage {
    public ServicePackage() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "serv_pckg_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "serv_pckg_id"),
            inverseJoinColumns = @JoinColumn(name = "val_period_id")
    )
    private Set<ValidityPeriod> validityPeriods;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "serv_pckg_id"),
            inverseJoinColumns = @JoinColumn(name = "opt_prod_id")
    )
    private Set<OptionalProduct> optionalProducts;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "serv_pckg_id"
    )
    private Set<SubscriptionOrder> subscriptionOrders;

    // GETTERS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Set<ValidityPeriod> getValidityPeriods() {
        return validityPeriods;
    }

    public Set<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public Set<SubscriptionOrder> getSubscriptionOrders() {
        return subscriptionOrders;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public void setValidityPeriods(Set<ValidityPeriod> validityPeriods) {
        this.validityPeriods = validityPeriods;
    }

    public void setOptionalProducts(Set<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public void setSubscriptionOrders(Set<SubscriptionOrder> subscriptionOrders) {
        this.subscriptionOrders = subscriptionOrders;
    }
}
