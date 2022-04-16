package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.SubscriptionOrder;

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

    public int getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(int subOrderId) {
        this.subOrderId = subOrderId;
    }
}
