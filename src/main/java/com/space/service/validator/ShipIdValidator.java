package com.space.service.validator;

import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ShipIdValidator {

    private ShipService shipService;

    private static final Integer MIN_ID_VALUE = 0;

    @Autowired
    public ShipIdValidator(ShipService shipService) {
        this.shipService = shipService;
    }

    public boolean nonExists(Long id) {
        return isNull(id) || !shipService.existsById(id);
    }

    public boolean nonValid(Long id) {
        return isNull(id) || id <= MIN_ID_VALUE;
    }
}
