package com.kosign.redoapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreationTimestamp
    @Column(updatable = false, name = "regi_dtm")
    private LocalDateTime registerDateTime;

    @CreatedBy
    @Column(updatable = false, name = "regi_by")
    private Long registerBy;

    @UpdateTimestamp
    @Column(name = "chng_dtm")
    private LocalDateTime changeDateTime;

    @LastModifiedBy
    @Column(insertable = false, name = "chng_by")
    private Long changeBy;


}
