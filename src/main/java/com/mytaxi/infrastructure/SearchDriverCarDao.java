package com.mytaxi.infrastructure;

import com.mytaxi.application.dto.DriverQueryRequest;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverCarSelection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDriverCarDao
{

    @PersistenceContext
    private EntityManager entityManager;


    public List<Driver> searchDriver(DriverQueryRequest searchRequest)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);

        final Root<Driver> fromDrivers = criteriaQuery.from(Driver.class);
        final Root<Car> fromCars = criteriaQuery.from(Car.class);
        final Root<DriverCarSelection> fromDriverCarSelection = criteriaQuery.from(DriverCarSelection.class);

        List<Predicate> criteriaList = new ArrayList<>();

        // where Driver.id = DriverCarSelecion.driver_id
        Predicate p1 = criteriaBuilder.equal(
            fromDrivers.get("id"), fromDriverCarSelection.get("driverId")
        );
        criteriaList.add(p1);

        // where Car.id = DriverCarSelecion.car_id
        Predicate p2 = criteriaBuilder.equal(
            fromCars.get("id"), fromDriverCarSelection.get("carId")
        );
        criteriaList.add(p2);

        // username
        searchRequest.getUsername().ifPresent(username ->
            criteriaList.add(criteriaBuilder.equal(fromDrivers.get("username"), username)));
        // online status
        searchRequest.getOnlineStatus().ifPresent(onlineStatus ->
            criteriaList.add(criteriaBuilder.equal(fromDrivers.get("onlineStatus"), onlineStatus)));

        // car attributes
        // engineType
        searchRequest.getEngineType().ifPresent(engineType ->
            criteriaList.add(criteriaBuilder.equal(fromCars.get("engineType"), engineType)));

        // licensePlate
        searchRequest.getLicensePlate().ifPresent(licensePlate ->
            criteriaList.add(criteriaBuilder.equal(fromCars.get("licensePlate"), licensePlate)));

        // seat count
        searchRequest.getSeatCount().ifPresent(seatCount ->
            criteriaList.add(criteriaBuilder.equal(fromCars.get("seatCount"), seatCount)));

        searchRequest.getManufacturer().ifPresent(manufacturer ->
            criteriaList.add(criteriaBuilder.equal(fromCars.get("manufacturer"), manufacturer)));

        // select
        criteriaQuery.select(criteriaBuilder.construct(Driver.class, fromDrivers.get("id"), fromDrivers.get("username"), fromDrivers.get("onlineStatus")));

        // apply predicates
        criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));

        final TypedQuery query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
