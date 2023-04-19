package com.challenge.darien.dominio;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class AuditableBase {

    /*@CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createBy;*/

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Timestamp createAt;

    /*@LastModifiedBy
    @Column(name = "modify_by")
    private String modifiedBy;*/

    @LastModifiedDate
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

}
