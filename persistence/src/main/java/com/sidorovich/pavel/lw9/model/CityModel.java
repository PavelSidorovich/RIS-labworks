package com.sidorovich.pavel.lw9.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = CityModel_.TABLE)
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CityModel_.ID, nullable = false)
    private Long id;

    @Column(name = CityModel_.CITY_NAME, nullable = false, length = 256)
    private String cityName;

}