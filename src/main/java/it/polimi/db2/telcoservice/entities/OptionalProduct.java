package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "optional_product")
public class OptionalProduct {
    public OptionalProduct() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column(name = "monthly_fee")
    private BigDecimal monthlyFee;
    @ManyToMany
    @JoinTable(
            name = "prod_associated_with",
            joinColumns = @JoinColumn(name = "op_prodID"),
            inverseJoinColumns = @JoinColumn(name = "serv_pckgID")
    )
    private Set<ServicePackage> servicePackages;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Set<ServicePackage> getServicePackages() {
        return servicePackages;
    }

    public void setServicePackages(Set<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }
}
