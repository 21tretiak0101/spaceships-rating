package com.space.controller;

import com.space.exception.InvalidShipFieldException;
import com.space.exception.InvalidShipIdException;
import com.space.exception.ShipExistException;
import com.space.model.Ship;
import com.space.model.ShipOrder;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.service.validator.NullValidator;
import com.space.service.validator.ShipIdValidator;
import com.space.service.validator.ShipValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.space.model.ShipSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
@RequestMapping("/rest")
public class ShipController {

    private static final String NOT_FOUND_BY_ID_MESSAGE = "Ship doesn't exist with ID: ";

    private static final String INVALID_ID_MESSAGE = "Invalid ship ID: ";

    private static Logger log = Logger.getLogger(ShipController.class);

    private ShipService shipService;

    private ShipValidator shipValidator;

    private NullValidator nullValidator;

    private ShipIdValidator shipIdValidator;

    @Autowired
    public ShipController(ShipService shipService, ShipValidator shipValidator,
                          NullValidator nullValidator, ShipIdValidator shipIdValidator) {
        this.shipService = shipService;
        this.shipValidator = shipValidator;
        this.nullValidator = nullValidator;
        this.shipIdValidator = shipIdValidator;
    }

    @GetMapping("/ships")
    public ResponseEntity<List<Ship>> getShips (
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "planet", required = false) String planet,
            @RequestParam(name = "shipType", required = false) ShipType shipType,
            @RequestParam(name = "after", required = false) Long after,
            @RequestParam(name = "before", required = false) Long before,
            @RequestParam(name = "isUsed", required = false) Boolean isUsed,
            @RequestParam(name = "minSpeed", required = false) Double minSpeed,
            @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(name = "maxCrewSize", required = false ) Integer maxCrewSize,
            @RequestParam(name = "minRating", required = false) Double minRating,
            @RequestParam(name = "maxRating", required = false) Double maxRating,
            @RequestParam(name = "order", required = false, defaultValue = "ID") ShipOrder order,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize ) {

        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));

        Page<Ship> shipsPage = shipService.findAll(
                where(
                        shipsLikeName(name)
                                .and(shipsLikePlanet(planet))
                                .and(shipsEqualShipType(shipType))
                                .and(shipsGreaterThanOrEqualToDate(after))
                                .and(shipsLessThanOrEqualToDate(before))
                                .and(shipsEqualUsed(isUsed))
                                .and(shipsGreaterThanOrEqualToSpeed(minSpeed))
                                .and(shipsLessThanOrEqualToSpeed(maxSpeed))
                                .and(shipsGreaterThanOrEqualToCrewSize(minCrewSize))
                                .and(shipsLessThanOrEqualToCrewSize(maxCrewSize))
                                .and(shipsGreaterThanOrEqualToRating(minRating))
                                .and(shipsLessThanOrEqualToRating(maxRating))
                ),
                pageRequest);

        return new ResponseEntity<>(shipsPage.getContent(), HttpStatus.OK);
    }

    @GetMapping("/ships/count")
    public Long getShipsCount(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "planet", required = false) String planet,
            @RequestParam(name = "shipType", required = false) ShipType shipType,
            @RequestParam(name = "after", required = false) Long after,
            @RequestParam(name = "before", required = false) Long before,
            @RequestParam(name = "isUsed", required = false) Boolean isUsed,
            @RequestParam(name = "minSpeed", required = false) Double minSpeed,
            @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(name = "maxCrewSize", required = false ) Integer maxCrewSize,
            @RequestParam(name = "minRating", required = false) Double minRating,
            @RequestParam(name = "maxRating", required = false) Double maxRating) {

        Specification<Ship> shipSpecification = where(
                shipsLikeName(name)
                        .and(shipsLikePlanet(planet))
                        .and(shipsEqualShipType(shipType))
                        .and(shipsGreaterThanOrEqualToDate(after))
                        .and(shipsLessThanOrEqualToDate(before))
                        .and(shipsEqualUsed(isUsed))
                        .and(shipsGreaterThanOrEqualToSpeed(minSpeed))
                        .and(shipsLessThanOrEqualToSpeed(maxSpeed))
                        .and(shipsGreaterThanOrEqualToCrewSize(minCrewSize))
                        .and(shipsLessThanOrEqualToCrewSize(maxCrewSize))
                        .and(shipsGreaterThanOrEqualToRating(minRating))
                        .and(shipsLessThanOrEqualToRating(maxRating)));

        return shipService.getShipsCount(shipSpecification);
    }


    @GetMapping("/ships/{id}")
    public ResponseEntity<Ship> getShip(@PathVariable Long id) {

        if (shipIdValidator.nonValid(id)) {
            throw new InvalidShipIdException(INVALID_ID_MESSAGE + id);
        }

        Ship ship = shipService.getShipById(id).orElseThrow(
                () -> new ShipExistException(NOT_FOUND_BY_ID_MESSAGE + id));

        return new ResponseEntity<>(ship, HttpStatus.OK);
    }


    @PostMapping("/ships")
    public ResponseEntity<Ship> saveShip(@RequestBody Ship shipBody, BindingResult errorsResolver) {

        shipValidator.validate(shipBody, errorsResolver);
        nullValidator.validate(shipBody, errorsResolver);
        if (errorsResolver.hasErrors()) {
            throw new InvalidShipFieldException(errorsResolver);
        }
        Ship ship = shipService.saveShip(shipBody);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/ships/{id}")
    public void deleteShip(@PathVariable  Long id) {

        if (shipIdValidator.nonValid(id)) {
            throw new InvalidShipIdException(INVALID_ID_MESSAGE + id);
        }
        if (shipIdValidator.nonExists(id)) {
            throw new ShipExistException(NOT_FOUND_BY_ID_MESSAGE + id);
        }

        shipService.deleteShip(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/ships/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable Long id, @RequestBody Ship shipBody,
                                           BindingResult errorsResolver) {

        if (shipIdValidator.nonValid(id)) {
            throw new InvalidShipIdException(INVALID_ID_MESSAGE + id);
        }

        shipValidator.validate(shipBody, errorsResolver);

        if (errorsResolver.hasErrors()) {
            throw new InvalidShipFieldException(errorsResolver);
        }

        Ship ship = shipService.updateShip(id, shipBody).orElseThrow(
                () -> new ShipExistException(NOT_FOUND_BY_ID_MESSAGE + id));


        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @ExceptionHandler({InvalidShipIdException.class, InvalidShipFieldException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequest (Exception e) {
        return Map.of("message", e.getMessage(),
                "error", e.getClass().getSimpleName());
    }

    @ExceptionHandler(ShipExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFound (Exception e) {
        return Map.of("message", e.getMessage(),
                "error", e.getClass().getSimpleName());
    }
}
