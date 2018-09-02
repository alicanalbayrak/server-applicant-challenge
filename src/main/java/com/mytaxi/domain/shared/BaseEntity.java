package com.mytaxi.domain.shared;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity<T extends BaseEntity<T>> implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "DateCreated", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private Boolean deleted = false;


    public void setId(Long id)
    {
        this.id = id;
    }


    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }


    /**
     * Checks the passed entity, if it has an identity. It gets an identity only by saving.
     *
     * @throws IllegalStateException the passed entity does not have the identity attribute set.
     */
    private void _checkIdentity(final BaseEntity<?> entity)
    {
        if (entity.getId() == null)
        {
            throw new IllegalStateException("Identity missing in entity: " + entity);
        }
    }


    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }


    @Override
    public boolean equals(final Object object)
    {
        if (!(object instanceof BaseEntity))
        {
            return false;
        }
        final BaseEntity<?> that = (BaseEntity<?>) object;
        _checkIdentity(this);
        _checkIdentity(that);
        return this.id.equals(that.getId());
    }
}
