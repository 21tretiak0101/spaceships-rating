package com.space.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

@Entity
@Table(name = "ship")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "planet")
    private String planet;

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column(name = "prodDate")
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;


    public Ship() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }

    public void calculateRating() {
        final int CURRENT_YEAR = 3019;

        Calendar shipProdTime = Calendar.getInstance();
        shipProdTime.setTime(prodDate);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);

        final double newRating = (80 * speed * (isUsed ? 0.5 : 1))
                / (CURRENT_YEAR - calendar.get(Calendar.YEAR) + 1);

        this.setRating((Math.round(newRating * 100.0) / 100.0));
    }

    public void updateFields(Ship newShip) {

        if (hasText(newShip.name))
            name = newShip.name;

        if (hasText(newShip.planet))
            planet = newShip.planet;

        if (nonNull(newShip.shipType))
            shipType = newShip.shipType;

        if (nonNull(newShip.prodDate))
            prodDate = newShip.prodDate;

        if (nonNull(newShip.isUsed))
            isUsed = newShip.isUsed;

        if (nonNull(newShip.speed))
            speed = newShip.speed;

        if (nonNull(newShip.crewSize))
            crewSize = newShip.crewSize;
    }

    public void setDefaultUsed(){
        if (isNull(isUsed)) isUsed = false;
    }
}
