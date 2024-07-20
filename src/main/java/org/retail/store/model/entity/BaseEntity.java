package org.retail.store.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    protected Instant createdAt;

    @Column(name = "updated_at",columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    protected Instant updatedAt;
}
