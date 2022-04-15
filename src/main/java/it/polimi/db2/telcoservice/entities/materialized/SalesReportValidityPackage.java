package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_validity_packages")
@IdClass(SalesReportValidityPackageId.class)
public class SalesReportValidityPackage {
    public SalesReportValidityPackage() {
    }

    @Id
    @Column(
            nullable = false,
            name = "serv_pckg_id"
    )
    private int servPckgId;

    @Id
    @Column(
            nullable = false,
            name = "val_period_id"
    )
    private int valPeriodId;

    @Column()
    private int purchases;

    public int getServPckgId() {
        return servPckgId;
    }

    public void setServPckgId(int servPckgId) {
        this.servPckgId = servPckgId;
    }

    public int getValPeriodId() {
        return valPeriodId;
    }

    public void setValPeriodId(int valPeriodId) {
        this.valPeriodId = valPeriodId;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }
}
