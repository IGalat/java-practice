package red.diploma.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @JsonIgnore
    @CreatedBy
    @Column(updatable = false, nullable = false)
    protected U createdBy;

    @JsonIgnore
    @CreatedDate
    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @JsonIgnore
    @LastModifiedBy
    @Column(nullable = false)
    protected U changedBy;

    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date changedDate;
}
