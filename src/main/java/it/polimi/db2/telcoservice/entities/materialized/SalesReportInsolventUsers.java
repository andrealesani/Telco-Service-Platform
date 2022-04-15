package it.polimi.db2.telcoservice.entities.materialized;

import it.polimi.db2.telcoservice.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_insolvent_users")
public class SalesReportInsolventUsers {
    public SalesReportInsolventUsers() {
    }

    @Id
    @Column(
            nullable = false,
            name = "user_id"
    )
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
