package com.mytaxi.application.controller;

import com.mytaxi.application.dto.DriverCarSelectDTO;
import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.application.service.DriverService;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.CarAlreadyInUseException;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.DriverAlreadySelectedCarException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import com.mytaxi.domain.shared.OfflineDriverCarSelectionException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return driverService.find(driverId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        return driverService.create(driverDTO);
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @PostMapping("/{driverId}/selectCar/{carId}")
    public DriverCarSelectDTO selectCar(@RequestParam long driverId, @RequestParam long carId) throws CarAlreadyInUseException, EntityNotFoundException,
                                                                                                      OfflineDriverCarSelectionException, DriverAlreadySelectedCarException
    {
        return driverService.selectCar(driverId, carId);
    }


    @DeleteMapping("/{driverId}/deselectCar")
    public void deselectCar(@RequestParam long driverId) throws EntityNotFoundException
    {
        driverService.deselectCar(driverId);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam Optional<OnlineStatus> onlineStatus, @RequestParam Optional<String> username)
    {
        return driverService.find(onlineStatus.orElse(null), username.orElse(null));
    }
}
