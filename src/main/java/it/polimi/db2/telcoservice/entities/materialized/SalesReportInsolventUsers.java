package it.polimi.db2.telcoservice.entities.materialized;

import javax.persistence.*;

@Entity
@Table(name = "sales_report_insolvent_users")
@NamedQueries({
        @NamedQuery(name = "SalesReportInsolventUsers.findAllInsolvent", query = "SELECT sriu FROM SalesReportInsolventUsers sriu"),

})
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
