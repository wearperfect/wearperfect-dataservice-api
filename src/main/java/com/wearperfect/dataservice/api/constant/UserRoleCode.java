package com.wearperfect.dataservice.api.constant;

public enum UserRoleCode {
    GUEST("GUEST"),
    USER("USER"),
    BRAND("BRAND"),
    DESIGNER("DSGNR"),
    ADMIN("ADMIN");
    private final String value;

    UserRoleCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
