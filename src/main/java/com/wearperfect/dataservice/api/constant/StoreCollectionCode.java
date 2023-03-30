package com.wearperfect.dataservice.api.constant;

public enum StoreCollectionCode {
    FEATURED("FTRD"),
    WHATS_HOT("WHOT"),
    SEASONAL("SNSL"),
    FESTIVE("FEST"),
    LATEST("LTST");
    private final String value;

    StoreCollectionCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
