package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_insolvent_users")
public class SalesReportInsolventUsers {
    public SalesReportInsolventUsers() {
    }

    @Id
    @OneToOne
    @Column(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
