package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_report_packages")
@NamedQueries({
        @NamedQuery(name = "SalesReportPackages.findAllSalesReports", query = "SELECT srp FROM SalesReportPackages srp"),

})
public class SalesReportPackages {
    public SalesReportPackages() {
    }

    @Id
    @Column(
            nullable = false,
            name = "serv_pckg_id"
    )
    private int servPckgId;

    @Column()
    private int purchases;
    @Column(name = "sales_value_no_products")
    private BigDecimal salesValueNoProducts;
    @Column(name = "sales_value_with_products")
    private BigDecimal salesValueWithProducts;
    @Column(name = "avg_num_products")
    private BigDecimal avgNumProducts;

    public int getServPckgId() {
        return servPckgId;
    }

    public void setServPckgId(int servPckgId) {
        this.servPckgId = servPckgId;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public BigDecimal getSalesValueNoProducts() {
        return salesValueNoProducts;
    }

    public void setSalesValueNoProducts(BigDecimal salesValueNoProducts) {
        this.salesValueNoProducts = salesValueNoProducts;
    }

    public BigDecimal getSalesValueWithProducts() {
        return salesValueWithProducts;
    }

    public void setSalesValueWithProducts(BigDecimal salesValueWithProducts) {
        this.salesValueWithProducts = salesValueWithProducts;
    }

    public BigDecimal getAvgNumProducts() {
        return avgNumProducts;
    }

    public void setAvgNumProducts(BigDecimal avgNumProducts) {
        this.avgNumProducts = avgNumProducts;
    }
}
