package com.space.service.validator;

import com.space.model.Ship;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class ShipValidator implements Validator {

    private static final int MIN_SYMBOLS_VALUE = 0;

    private static final int MAX_SYMBOLS_VALUE = 50;

    private static final int MIN_PROD_YEAR = 2800;

    private static final int MAX_PROD_YEAR = 3019;

    private static final int MIN_CREW_SIZE = 1;

    private static final int MAX_CREW_SIZE = 9999;

    private static final double MIN_SPEED = 0.01;

    private static final double MAX_SPEED = 0.99;

    private static final String ERROR_MESSAGE = "Invalid field value: '%s'";

    @Override
    public boolean supports(Class<?> aClass) {
        return Ship.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Ship s = (Ship) o;

        if (nonValidName(s.getName()))
            errors.reject("name", message("name"));

        if (nonValidPlanet(s.getPlanet()))
            errors.reject("planet", message("planet"));

        if (nonValidCrewSize(s.getCrewSize()))
            errors.reject("crewSize", message("crewSize"));

        if (nonValidProdYear(s.getProdDate()))
            errors.reject("prodYear", message("prodYear"));

        if (nonValidSpeed(s.getSpeed()))
            errors.reject("speed", message("speed"));
    }


    public static boolean nonValidSpeed(Double speed) {
        if (isNull(speed)) return false;

        return speed < MIN_SPEED || speed > MAX_SPEED;
    }

    public static boolean nonValidCrewSize(Integer crewSize) {
        if (isNull(crewSize)) return false;

        return crewSize < MIN_CREW_SIZE || crewSize > MAX_CREW_SIZE;
    }

    public static boolean nonValidName(String name) {
        if (isNull(name)) return false;

        return name.length() == MIN_SYMBOLS_VALUE || name.length() > MAX_SYMBOLS_VALUE;
    }

    public static boolean nonValidPlanet(String planet) {
        if (isNull(planet)) return false;

        return planet.length() == MIN_SYMBOLS_VALUE || planet.length() > MAX_SYMBOLS_VALUE;
    }

    public static boolean nonValidProdYear(Date prodDate) {
        if (isNull(prodDate)) return false;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);

        int prodYear = calendar.get(Calendar.YEAR);

        return prodYear < MIN_PROD_YEAR || prodYear > MAX_PROD_YEAR;
    }

    private static String message(String fieldName) {
        return String.format(ERROR_MESSAGE, fieldName);
    }
}
