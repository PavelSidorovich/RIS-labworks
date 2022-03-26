package com.sidorovich.pavel.lw8.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Data
@NoArgsConstructor(force = true)
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @XmlTransient
    private Long id;

    @Column(name = "city_name", nullable = false, length = 256)
    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

}