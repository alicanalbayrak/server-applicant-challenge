package com.mytaxi.application.controller;

import com.mytaxi.application.mapper.DriverMapper;
import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverDomainService;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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

    private final DriverDomainService driverDomainService;


    @Autowired
    public DriverController(final DriverDomainService driverDomainService)
    {
        this.driverDomainService = driverDomainService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.toDto(driverDomainService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        Driver driver = DriverMapper.toEntity(driverDTO);
        return DriverMapper.toDto(driverDomainService.create(driver));
    }


    @DeleteMapping("/{driverId}")
    @Transactional
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverDomainService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    @Transactional
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverDomainService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return DriverMapper.toDtoList(driverDomainService.find(onlineStatus));
    }
}
