package it.polimi.db2.telcoservice.entities.materialized;

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

    @Column(name = "total_sales")
    private BigDecimal totalSales;

    public int getOptProdId() {
        return optProdId;
    }

    public void setOptProdId(int optProdId) {
        this.optProdId = optProdId;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}
