package it.polimi.db2.telcoservice.entities.materialized;


import java.io.Serializable;

public class SalesReportValidityPackagesId implements Serializable {
    public SalesReportValidityPackagesId() {
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
