package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_media", schema = "wearperfect")
public class ProductMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Size(max = 1024)
    @Column(name = "title", length = 1024)
    private String title;

    @Size(max = 4096)
    @Column(name = "`desc`", length = 4096)
    private String desc;

    @NotNull
    @Column(name = "sequence_id", nullable = false)
    private Byte sequenceId;

    @Size(max = 1024)
    @NotNull
    @Column(name = "source_link", nullable = false, length = 1024)
    private String sourceLink;

    @NotNull
    @Column(name = "content_type_id", nullable = false)
    private Integer contentTypeId;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ContentType.class)
    @JoinColumn(name = "content_type_id", nullable = false, insertable = false, updatable = false)
    private ContentType contentType;

}