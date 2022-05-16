package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_suspended_orders")
@NamedQueries({
        @NamedQuery(name = "SalesReportSuspendedOrders.findAllSuspended", query = "SELECT srso FROM SalesReportSuspendedOrders srso"),

})
public class SalesReportSuspendedOrders {
    public SalesReportSuspendedOrders() {
    }

    @Id
    @Column(
            nullable = false,
            name = "sub_order_id"
    )
    private int subOrderId;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @MapsId
    @JoinColumn(
            name = "sub_order_id",
            nullable = false
    )
    private SubscriptionOrder subscriptionOrder;

    public int getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(int subOrderId) {
        this.subOrderId = subOrderId;
    }

    public SubscriptionOrder getSubscriptionOrder() {
        return subscriptionOrder;
    }

    public void setSubscriptionOrder(SubscriptionOrder subscriptionOrder) {
        this.subscriptionOrder = subscriptionOrder;
    }
}
