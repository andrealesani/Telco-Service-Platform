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

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @MapsId
    @JoinColumn(
            name = "serv_pckg_id",
            nullable = false
    )
    private ServicePackage servicePackage;

    @Column(
            nullable = false
    )
    private int purchases;
    @Column(
            nullable = false,
            name = "sales_value_no_products")
    private BigDecimal salesValueNoProducts;
    @Column(
            nullable = false,
            name = "sales_value_with_products")
    private BigDecimal salesValueWithProducts;
    @Column(
            nullable = false,
            name = "avg_num_products")
    private BigDecimal avgNumProducts;

    public int getServPckgId() {
        return servPckgId;
    }

    public void setServPckgId(int servPckgId) {
        this.servPckgId = servPckgId;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
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
