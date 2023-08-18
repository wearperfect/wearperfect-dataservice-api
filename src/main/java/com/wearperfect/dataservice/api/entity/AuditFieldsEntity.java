package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.Column;

import java.util.Date;

public class AuditFieldsEntity {
    @Column(name = "last_updated_by")
    Long lastUpdatedBy;

    @Column(name = "last_updated_on")
    Date lastUpdatedOn;

    @Column(name = "last_used_on")
    Date lastUsedOn;

    @Column(name = "active")
    Boolean active;
}
