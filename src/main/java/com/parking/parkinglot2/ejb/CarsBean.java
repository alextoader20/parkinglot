package com.parking.parkinglot2.ejb;
import com.parking.parkinglot2.common.CarDTO;
import com.parking.parkinglot2.entities.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());


    @PersistenceContext
    EntityManager entityManager;

    public List<CarDTO> findAllCars(){
        LOG.info("find all cars!");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    public List<CarDTO> copyCarsToDto(List<Car> cars) {
        List<CarDTO> carDtos = new ArrayList<>();
        for(Car car : cars) {
            CarDTO carDto = new CarDTO(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getOwner().getUsername(),
                    car.getParkingSpot()
            );
            carDtos.add(carDto);
        }
        return carDtos;
    }
}
