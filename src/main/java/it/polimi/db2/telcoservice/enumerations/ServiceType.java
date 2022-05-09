package it.polimi.db2.telcoservice.enumerations;

public enum ServiceType {
    MOBILE_INTERNET,
    FIXED_INTERNET,
    MOBILE_PHONE,
    FIXED_PHONE;

    @Override
    public String toString() {
        if (this == ServiceType.MOBILE_PHONE) {
            return "Mobile Phone";
        }
        if (this == ServiceType.FIXED_INTERNET) {
            return "Fixed Internet";
        }
        if (this == ServiceType.MOBILE_INTERNET) {
            return "Mobile Internet";
        }
        if (this == ServiceType.FIXED_PHONE) {
            return "Fixed Phone";
        }
        return "";
    }
}
