package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.SubscriptionOrder;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_suspended_orders")
public class SalesReportSuspendedOrders {
    public SalesReportSuspendedOrders() {
    }

    @Id
    @OneToOne
    @Column(
            nullable = false,
            name = "sub_order_id"
    )
    private SubscriptionOrder subscriptionOrder;
}
