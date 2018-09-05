package com.mytaxi.application.service;

import com.mytaxi.application.dto.DriverCarSelectDTO;
import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.application.dto.DriverQueryRequest;
import com.mytaxi.application.dto.DriverQueryResponse;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.CarAlreadyInUseException;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.DriverAlreadySelectedCarException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import com.mytaxi.domain.shared.OfflineDriverCarSelectionException;
import java.util.List;

public interface DriverService
{

    DriverDTO find(Long driverId) throws EntityNotFoundException;

    DriverDTO create(DriverDTO DriverDTO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDTO> find(OnlineStatus onlineStatus);

    DriverCarSelectDTO selectCar(long driverId, long carId) throws DriverAlreadySelectedCarException, CarAlreadyInUseException, OfflineDriverCarSelectionException,
                                                                   EntityNotFoundException;

    void deselectCar(long driverId) throws EntityNotFoundException;

    List<DriverDTO> find(OnlineStatus onlineStatus, String username);

    List<DriverQueryResponse> searchDriver(DriverQueryRequest driverQuerRequest);
}
