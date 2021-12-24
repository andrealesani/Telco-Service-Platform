package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
@NamedQueries({})
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private int userId;
    @Column(name = "valid_perID")
    private int validPerId;
    @Column
    private int orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getValidPerId() {
        return validPerId;
    }

    public void setValidPerId(int validPerId) {
        this.validPerId = validPerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
