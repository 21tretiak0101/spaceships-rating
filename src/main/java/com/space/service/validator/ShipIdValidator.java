package com.space.service.validator;

import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ShipIdValidator {

    private ShipService shipService;

    @Autowired
    public ShipIdValidator(ShipService shipService) {
        this.shipService = shipService;
    }

    public boolean nonExists(Long id) {
        return isNull(id) || !shipService.existById(id);
    }

    public boolean nonValid(Long id) {
        return isNull(id) || String.valueOf(id).contains(".") || id <= 0;
    }
}
