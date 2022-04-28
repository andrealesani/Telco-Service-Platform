package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "auditing")
@NamedQueries({
        @NamedQuery(name = "Auditing.findAllAuditings", query = "SELECT a FROM Auditing a"),
})
public class Auditing {
    public Auditing() {
    }

    public Auditing(Timestamp rejectionTs, BigDecimal rejectedAmount) {
        this.rejectionTs = rejectionTs;
        this.rejectedAmount = rejectedAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(
            nullable = false,
            name = "rejection_ts"
    )
    private Timestamp rejectionTs;
    @Column(
            nullable = false,
            name = "rejected_amount"
    )
    private BigDecimal rejectedAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public int getId() {
        return id;
    }

    public Timestamp getRejectionTs() {
        return rejectionTs;
    }

    public BigDecimal getRejectedAmount() {
        return rejectedAmount;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRejectionTs(Timestamp rejectionTs) {
        this.rejectionTs = rejectionTs;
    }

    public void setRejectedAmount(BigDecimal rejectedAmount) {
        this.rejectedAmount = rejectedAmount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
