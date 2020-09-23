package com.gab.domain;

import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "car")
public class Car {

    @Id
    private String id;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "brand", nullable = false)
    private String brand;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.ALL})
    private List<Passenger> passengers;
}
