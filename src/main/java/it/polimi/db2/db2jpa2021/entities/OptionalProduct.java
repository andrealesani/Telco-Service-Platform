package it.polimi.db2.db2jpa2021.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "optional_product")
public class OptionalProduct {
    public OptionalProduct() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private BigDecimal monthly_fee;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMonthly_fee() {
        return monthly_fee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonthly_fee(BigDecimal monthly_fee) {
        this.monthly_fee = monthly_fee;
    }
}
