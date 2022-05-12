package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "subscription_order")
@NamedQueries({
        @NamedQuery(name = "SubscriptionOrder.makePayment", query = "UPDATE SubscriptionOrder so SET so.valid = ?2, so.user = ?3 WHERE so.id = ?1")
})
public class SubscriptionOrder {
    public SubscriptionOrder() {
    }

    public SubscriptionOrder(ServicePackage servicePackage, ValidityPeriod validityPeriod, Set<OptionalProduct> optionalProducts, Timestamp creationTs, Timestamp startDateTs) {
        this.servicePackage = servicePackage;
        this.validityPeriod = validityPeriod;
        this.optionalProducts = optionalProducts;
        this.creationTs = creationTs;
        this.startDateTs = startDateTs;
        this.user = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(
            name = "total_value"
    )
    private BigDecimal totalValue;
    @Column(
            nullable = false,
            name = "creation_ts"
    )
    private Timestamp creationTs;
    @Column(
            name = "start_date_ts"
    )
    private Timestamp startDateTs;
    @Column()
    private Boolean valid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "serv_pckg_id"
    )
    private ServicePackage servicePackage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "val_period_id"
    )
    private ValidityPeriod validityPeriod;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "sub_order_id"),
            inverseJoinColumns = @JoinColumn(name = "opt_prod_id")
    )
    private Set<OptionalProduct> optionalProducts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    // GETTER

    public int getId() {
        return id;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Timestamp getCreationTs() {
        return creationTs;
    }

    public Timestamp getStartDateTs() {
        return startDateTs;
    }

    public Timestamp getDeactivationDateTs() {

        if (startDateTs == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startDateTs.getTime());
        cal.add(Calendar.MONTH, validityPeriod.getMonths());

        return new Timestamp(cal.getTime().getTime());
    }

    public boolean isValid() {
        return valid;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public Set<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public User getUser() {
        return user;
    }

    // SETTER

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void setCreationTs(Timestamp creationTs) {
        this.creationTs = creationTs;
    }

    public void setStartDateTs(Timestamp startDateTs) {
        this.startDateTs = startDateTs;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public void setOptionalProducts(Set<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
