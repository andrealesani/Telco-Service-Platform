package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.ServicePackage;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_report_product_sales")
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
