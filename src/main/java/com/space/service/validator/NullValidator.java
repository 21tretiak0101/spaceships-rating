package com.space.service.validator;

import com.space.model.Ship;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;

@Service
public class NullValidator implements Validator {

    private static final String NULL_FIELD_MESSAGE = "Field '%s' is null";

    @Override
    public boolean supports(Class<?> aClass) {
        return Ship.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Ship s = (Ship) o;

        if (isNull(s.getName()))
            errors.reject("name", message("name"));

        if (isNull(s.getPlanet()))
            errors.reject("planet", message("planet"));

        if (isNull(s.getShipType()))
            errors.reject("shipType", message("shipType"));

        if (isNull(s.getProdDate()))
            errors.reject("prodDate", message("prodDate"));

        if (isNull(s.getSpeed()))
            errors.reject("speed", message("speed"));

        if (isNull(s.getCrewSize()))
            errors.reject("crewSize", message("crewSize"));
    }

    private static String message(String fieldName) {
        return String.format(NULL_FIELD_MESSAGE, fieldName);
    }
}
