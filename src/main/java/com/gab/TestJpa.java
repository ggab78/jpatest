package com.gab;

import com.gab.config.BeenConfig;
import com.gab.domain.Car;
import com.gab.repositories.CarRepository;
import com.gab.service.CarService;
import com.gab.service.Greeting;
import com.gab.service.ManagerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
@RequiredArgsConstructor
public class TestJpa {

    private static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {

        context = new AnnotationConfigApplicationContext(BeenConfig.class);

        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
        Greeting greeting = context.getBean(Greeting.class);


        ManagerImpl manager=new ManagerImpl(greeting);
        manager.println("##### GAB");

        Car car=Car.builder().brand("mazda").color("red").build();

        CarRepository carRepository = context.getBean(CarRepository.class);
        CarService carService = new CarService(carRepository);
        carService.saveCar(car);


    }

}
