package com.gab.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "passenger")
public class Passenger {

    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
}
