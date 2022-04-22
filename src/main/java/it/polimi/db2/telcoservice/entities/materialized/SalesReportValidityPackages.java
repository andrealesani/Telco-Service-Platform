package it.polimi.db2.telcoservice.entities.materialized;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_validity_packages")
@IdClass(SalesReportValidityPackagesId.class)
@NamedQueries({
        @NamedQuery(name = "SalesReportValidityPackages.findAllSalesReports", query = "SELECT srvp FROM SalesReportValidityPackages srvp"),

})
public class SalesReportValidityPackages {
    public SalesReportValidityPackages() {
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

    @Column(
            nullable=false
    )
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
