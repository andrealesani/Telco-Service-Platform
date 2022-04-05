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
    @OneToOne
    @Column(
            nullable = false,
            name = "opt_prod_id"
    )
    private OptionalProduct optionalProduct;

    @Column(name = "total_sales")
    private BigDecimal totalSales;
}
