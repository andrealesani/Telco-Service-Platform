package it.polimi.db2.telcoservice.entities.materialized;


import java.io.Serializable;

public class SalesReportValidityPackageId implements Serializable {
    public SalesReportValidityPackageId() {
    }
    private int servPckgId;
    private int valPeriodId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
