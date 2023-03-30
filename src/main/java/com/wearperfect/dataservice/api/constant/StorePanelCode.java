package com.wearperfect.dataservice.api.constant;

public enum StorePanelCode {
    LATEST("LTST"),
    COLLECTIONS("COLL"),
    DEALS("DEAL"),
    COMING_SOON("CMSN");
    private final String value;

    StorePanelCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
