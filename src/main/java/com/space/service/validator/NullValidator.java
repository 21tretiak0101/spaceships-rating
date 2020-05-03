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
    public void validate(Object o, Errors errorsResolver) {

        Ship s = (Ship) o;

        if (isNull(s.getName())) {
            errorsResolver.reject("name", message("name"));
        }

        if (isNull(s.getPlanet())) {
            errorsResolver.reject("planet", message("planet"));
        }

        if (isNull(s.getShipType())) {
            errorsResolver.reject("shipType", message("shipType"));
        }

        if (isNull(s.getProdDate())) {
            errorsResolver.reject("prodDate", message("prodDate"));
        }

        if (isNull(s.getSpeed())) {
            errorsResolver.reject("speed", message("speed"));
        }

        if (isNull(s.getCrewSize())) {
            errorsResolver.reject("crewSize", message("crewSize"));
        }
    }

    private static String message(String fieldName) {
        return String.format(NULL_FIELD_MESSAGE, fieldName);
    }
}
