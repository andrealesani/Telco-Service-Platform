package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
public class Service {
    public Service() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String type;
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

    @ManyToMany(mappedBy = "services")
    private Set<ServicePackage> servicePackages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGb() {
        return gb;
    }

    public void setGb(int gb) {
        this.gb = gb;
    }

    public BigDecimal getExtraGbFee() {
        return extraGbFee;
    }

    public void setExtraGbFee(BigDecimal extraGbFee) {
        this.extraGbFee = extraGbFee;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public BigDecimal getExtraMinFee() {
        return extraMinFee;
    }

    public void setExtraMinFee(BigDecimal extraMinFee) {
        this.extraMinFee = extraMinFee;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public BigDecimal getExtraSmsFee() {
        return extraSmsFee;
    }

    public void setExtraSmsFee(BigDecimal extraSmsFee) {
        this.extraSmsFee = extraSmsFee;
    }

    public Set<ServicePackage> getServicePackages() {
        return servicePackages;
    }

    public void setServicePackages(Set<ServicePackage> servicePackageSet) {
        this.servicePackages = servicePackageSet;
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
}
