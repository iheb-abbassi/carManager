package com.carManager.domainobject;

import com.carManager.domainvalue.EngineType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "car")
public class CarDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    private String licensePlate;

    private int seatCount;

    private boolean convertible;

    private int rating;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    private boolean deleted = false;


    public CarDO(final String licensePlate, final int seatCount, final boolean convertible, final int rating, final EngineType engineType)
    {
        this.dateCreated = ZonedDateTime.now();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
    }


    public CarDO()
    {

    }


    public Long getId()
    {
        return id;
    }


    public void setId(final Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(final ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(final String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public int getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(final int seatCount)
    {
        this.seatCount = seatCount;
    }


    public boolean isConvertible()
    {
        return convertible;
    }


    public void setConvertible(final boolean convertible)
    {
        this.convertible = convertible;
    }


    public int getRating()
    {
        return rating;
    }


    public void setRating(final int rating)
    {
        this.rating = rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(final EngineType engineType)
    {
        this.engineType = engineType;
    }


    public boolean isDeleted()
    {
        return deleted;
    }


    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }


}
