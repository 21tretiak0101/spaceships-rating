package com.space.service;

import com.space.model.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ShipService {

    Optional<Ship> getShipById(Long id);

    Ship saveShip(Ship ship);

    Optional<Ship> updateShip(Long id, Ship ship);

    void deleteShip(Long id);

    Boolean existsById(Long id);

    Page<Ship> findAll(Specification<Ship> specification, Pageable pageable);

    Long getShipsCount(Specification<Ship> specification);

}
