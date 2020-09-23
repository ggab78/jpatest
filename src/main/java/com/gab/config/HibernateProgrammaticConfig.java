package com.gab.config;

import com.gab.domain.Car;
import com.gab.domain.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.util.*;


public class HibernateProgrammaticConfig {


    public void bootstrapHibernateConfig(){

    }


    public static void main(String[] args) {

        AnnotationConfiguration config = new AnnotationConfiguration();
        config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        config.setProperty("hibernate.connection.username", "test");
        config.setProperty("hibernate.connection.password", "test");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.addPackage("com.gab.domain"); //the fully qualified package name
        config.addAnnotatedClass(Car.class);
        config.addAnnotatedClass(Passenger.class);



        try {
            SessionFactory sessionFactory = config.buildSessionFactory();

            Session session = sessionFactory.openSession();


            Transaction trx = session.beginTransaction();
            String uuid = UUID.randomUUID().toString();
            Car car = Car.builder().id(uuid).brand("fiat").color("red").build();

            Passenger passenger1 =Passenger.builder().id(UUID.randomUUID().toString()).name("Gab").build();
            Passenger passenger2 =Passenger.builder().id(UUID.randomUUID().toString()).name("ewa").build();

            passenger1.setCar(car);
            passenger2.setCar(car);

            List<Passenger>passengers = new ArrayList<>();
            passengers.add(passenger1);
            passengers.add(passenger2);
            car.setPassengers(passengers);


            session.save(car);



            //Car savedCar = (Car) session.get(Car.class, serializable);

            trx.commit();


            trx.begin();
            Car carnew = (Car) session.get(Car.class, uuid);

            trx.commit();

            carnew.getPassengers().forEach(p-> System.out.println(p.getName()+" : "+p.getCar().getBrand()));

            session.close();
            sessionFactory.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private static void generateSchema(){

            Map<String, String> settings = new HashMap<>();
            settings.put(Environment.URL, "jdbc:h2:mem:schema");

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(Car.class);
            metadataSources.addAnnotatedClass(Passenger.class);
            Metadata metadata = metadataSources.buildMetadata();

            SchemaExport schemaExport = new SchemaExport();
            schemaExport.setFormat(true);
            schemaExport.setOutputFile("create.sql");
            schemaExport.c
            schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata);
    }

}
