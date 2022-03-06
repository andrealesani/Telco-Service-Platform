package it.polimi.db2.telcoservice.entities;

import it.polimi.db2.telcoservice.enumerations.ServiceType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {
    public Service() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType type;
    @Column
    private int gb;
    @Column(name = "extra_gb_fee")
    private BigDecimal extraGbFee;
    @Column
    private int minutes;
    @Column(name = "extra_min_fee")
    private BigDecimal extraMinFee;
    @Column
    private int sms;
    @Column(name = "extra_sms_fee")
    private BigDecimal extraSmsFee;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "services"
    )
    private Set<ServicePackage> servicePackages;

    // GETTERS

    public int getId() {
        return id;
    }

    public ServiceType getType() {
        return type;
    }

    public int getGb() {
        return gb;
    }

    public BigDecimal getExtraGbFee() {
        return extraGbFee;
    }

    public int getMinutes() {
        return minutes;
    }

    public BigDecimal getExtraMinFee() {
        return extraMinFee;
    }

    public int getSms() {
        return sms;
    }

    public BigDecimal getExtraSmsFee() {
        return extraSmsFee;
    }

    public Set<ServicePackage> getServicePackages() {
        return servicePackages;
    }

    public String getFeesString() {
        String fees = "";
        if (gb != 0)
            fees += gb + " GBs. Extra fee per GB: " + extraGbFee + " euros ";
        if (minutes != 0)
            fees += minutes + " minutes. Extra fee per minute: " + extraMinFee + " euros ";
        if (sms != 0)
            fees += sms + " SMSs. Extra fee for SMS " + extraSmsFee + " euros";

        return fees;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public void setGb(int gb) {
        this.gb = gb;
    }

    public void setExtraGbFee(BigDecimal extraGbFee) {
        this.extraGbFee = extraGbFee;
    }

    public void setExtraMinFee(BigDecimal extraMinFee) {
        this.extraMinFee = extraMinFee;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public void setExtraSmsFee(BigDecimal extraSmsFee) {
        this.extraSmsFee = extraSmsFee;
    }

    public void setServicePackages(Set<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }
}
