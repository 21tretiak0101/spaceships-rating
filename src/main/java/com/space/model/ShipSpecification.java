package com.space.model;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

public class ShipSpecification {

    public static Specification<Ship> shipsLikeName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(name)) return criteriaBuilder.conjunction();
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Ship> shipsLikePlanet(String planet) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (isEmpty(planet)) return criteriaBuilder.conjunction();
            return criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
        };
    }

    public static Specification<Ship> shipsEqualShipType(ShipType shipType) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(shipType)) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("shipType"), shipType);
        };
    }

    public static Specification<Ship> shipsLessThanOrEqualToDate(Long date) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(date)) return criteriaBuilder.conjunction();
                return criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), new Date(date));

        };
    }

    public static Specification<Ship> shipsGreaterThanOrEqualToDate(Long date) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
        if (isNull(date)) return criteriaBuilder.conjunction();
                return criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), new Date(date));

        };
    }

    public static Specification<Ship> shipsEqualUsed(Boolean isUsed) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(isUsed)) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("isUsed"), isUsed);
        };
    }

    public static Specification<Ship> shipsLessThanOrEqualToSpeed(Double speed) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(speed)) return criteriaBuilder.conjunction();
            return criteriaBuilder.lessThanOrEqualTo(root.get("speed"), speed);
        };
    }

    public static Specification<Ship> shipsGreaterThanOrEqualToSpeed(Double speed) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(speed)) return criteriaBuilder.conjunction();
           return criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), speed);
        };
    }

    public static Specification<Ship> shipsLessThanOrEqualToCrewSize(Integer crewSize) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(crewSize)) return criteriaBuilder.conjunction();
            return criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), crewSize);
        };
    }

    public static Specification<Ship> shipsGreaterThanOrEqualToCrewSize(Integer crewSize) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(crewSize)) return criteriaBuilder.conjunction();
            return criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), crewSize);
        };
    }

    public static Specification<Ship> shipsLessThanOrEqualToRating(Double rating) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(rating)) return criteriaBuilder.conjunction();
            return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), rating);
        };
    }

    public static Specification<Ship> shipsGreaterThanOrEqualToRating(Double rating) {
        return (Specification<Ship>) (root, criteriaQuery, criteriaBuilder) -> {
            if (isNull(rating)) return criteriaBuilder.conjunction();
            return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
        };
    }
}
