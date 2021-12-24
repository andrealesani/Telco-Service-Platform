package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
}
