package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "validity_period")
@NamedQueries({})
public class ValidityPeriod {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private int months;
    @Column(name = "monthly_fee")
    private BigDecimal monthlyFee;
    @ManyToMany(mappedBy = "validityPeriods")
    private Set<ServicePackage> servicePackages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
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
