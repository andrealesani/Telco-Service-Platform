package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service_package")
@NamedQueries({
        @NamedQuery(name = "ServicePackage.findAllServicePackages", query = "SELECT sp FROM ServicePackage sp")
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
            name = "package_contains",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Service> services;

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
}
