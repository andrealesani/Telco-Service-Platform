package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_report_product_sales")
@NamedQueries({
        @NamedQuery(name = "SalesReportProductSales.findBestSeller", query = "" +
                "SELECT srps " +
                "FROM SalesReportProductSales srps " +
                "WHERE srps.totalSales = (" +
                "SELECT MAX(srps1.totalSales)" +
                "FROM SalesReportProductSales srps1" +
                ")"),

})
public class SalesReportProductSales {
    public SalesReportProductSales() {
    }

    @Id
    @Column(
            nullable = false,
            name = "opt_prod_id"
    )
    private int optProdId;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @MapsId
    @JoinColumn(
            name = "opt_prod_id",
            nullable = false
    )
    private OptionalProduct optionalProduct;

    @Column(
            nullable = false,
            name = "total_sales"
    )
    private BigDecimal totalSales;

    public int getOptProdId() {
        return optProdId;
    }

    public void setOptProdId(int optProdId) {
        this.optProdId = optProdId;
    }

    public OptionalProduct getOptionalProduct() {
        return optionalProduct;
    }

    public void setOptionalProduct(OptionalProduct optionalProduct) {
        this.optionalProduct = optionalProduct;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}
