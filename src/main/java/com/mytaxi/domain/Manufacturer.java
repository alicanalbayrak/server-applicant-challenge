package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "manufacturer",
    uniqueConstraints = @UniqueConstraint(name = "uc_manufacturer_name", columnNames = {"name"})
)
public class Manufacturer extends BaseEntity<Manufacturer>
{
    @Column(name = "Name")
    private String name;


    private Manufacturer(String name)
    {
        super(null, ZonedDateTime.now(), false);
        this.name = name;
    }

    public static Manufacturer createManufacturer(String name)
    {
        return new Manufacturer(name);
    }

}
