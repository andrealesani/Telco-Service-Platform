package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_validity_packages")
public class SalesReportValidityPackage {
    public SalesReportValidityPackage() {
    }

    @Id
    @OneToOne
    @Column(
            nullable = false,
            name = "serv_pckg_id"
    )
    private ServicePackage servicePackage;

    @Id
    @OneToOne
    @Column(
            nullable = false,
            name = "val_period_id"
    )
    private ValidityPeriod validityPeriod;

    @Column()
    private int purchases;
}
