package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2"),
        @NamedQuery(name = "User.existsUsername", query = "SELECT r FROM User r  WHERE r.username = ?1")
})
public class User {
    public User() {
    }

    public User(String username, String password, String email, boolean insolvent, int rejectedPayments) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.insolvent = insolvent;
        this.rejectedPayments = rejectedPayments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private boolean insolvent;
    @Column(name = "rejected_payments", nullable = false)
    private int rejectedPayments;
    @Column(name = "employee", nullable = false)
    private boolean employee;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<Auditing> auditing;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<SubscriptionOrder> orders;

    // GETTERS

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRejectedPayments() {
        return rejectedPayments;
    }

    public List<Auditing> getAuditing() {
        return auditing;
    }

    public List<SubscriptionOrder> getOrders() {
        return orders;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRejectedPayments(int rejectedPayments) {
        this.rejectedPayments = rejectedPayments;
    }

    public void setAuditing(List<Auditing> auditing) {
        this.auditing = auditing;
    }

    public void setOrders(List<SubscriptionOrder> orders) {
        this.orders = orders;
    }

    public boolean isInsolvent() {
        return insolvent;
    }

    public void setInsolvent(boolean insolvent) {
        this.insolvent = insolvent;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }
}
