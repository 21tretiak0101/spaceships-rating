package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public Optional<Ship> getShipById(Long id) {
        return shipRepository.findById(id);
    }

    @Override
    public Ship saveShip(Ship ship) {
        ship.setDefaultUsed();
        ship.calculateRating();
        return shipRepository.save(ship);
    }

    @Override
    public Optional<Ship> updateShip(Long id, Ship ship) {
        Optional<Ship> optionalShip = getShipById(id);
        if (!optionalShip.isPresent()) {
            return Optional.empty();
        }
        Ship s = optionalShip.get();
        s.update(ship);
        s.calculateRating();
        shipRepository.save(s);
        return Optional.of(s);
    }

    @Override
    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return shipRepository.existsById(id);
    }

    @Override
    public Page<Ship> findAll(Specification<Ship> specification, Pageable pageRequest) {
        return shipRepository.findAll(specification, pageRequest);
    }

    @Override
    public Long getShipsCount(Specification<Ship> specification) {
        return shipRepository.count(specification);
    }
}
