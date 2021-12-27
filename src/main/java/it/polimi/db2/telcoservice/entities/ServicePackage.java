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

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private int subsID;
    @ManyToMany
    @JoinTable(
            name = "pckg_contains",
            joinColumns = @JoinColumn(name = "serv_pckgID"),
            inverseJoinColumns = @JoinColumn(name = "servID"))
    private Set<Service> services;
    @ManyToMany
    @JoinTable(
            name = "pckg_lasts",
            joinColumns = @JoinColumn(name = "serv_pckgID"),
            inverseJoinColumns = @JoinColumn(name = "valid_perID")
    )
    private Set<ValidityPeriod> validityPeriods;
    @ManyToMany(mappedBy = "servicePackages")
    private Set<OptionalProduct> optionalProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubsID() {
        return subsID;
    }

    public void setSubsID(int subsID) {
        this.subsID = subsID;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<ValidityPeriod> getValidityPeriods() {
        return validityPeriods;
    }

    public void setValidityPeriods(Set<ValidityPeriod> validityPeriods) {
        this.validityPeriods = validityPeriods;
    }

    public List<Service> servicesList() {
        return new ArrayList<>(services);
    }

    public Set<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(Set<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }
}
