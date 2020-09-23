package com.gab.service;


import com.gab.domain.Car;
import com.gab.repositories.CarRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarService {


    private final CarRepository carRepository;

    public Car saveCar(Car car){
        //return carRepository.saveAndFlush(car);
        return null;
    }

}
