package com.sidorovich.pavel.lw9.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = UserModel_.TABLE)
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserModel_.ID, nullable = false)
    private Long id;

    @Column(name = UserModel_.FIRST_NAME, nullable = false, length = 256)
    private String firstName;

    @Column(name = UserModel_.IS_MALE, nullable = false)
    private Boolean isMale;

    @Column(name = UserModel_.ID_NUMBER, length = 6)
    private String idNumber;

    @Column(name = UserModel_.HOME_TEL, length = 8)
    private String homeTel;

    @ManyToOne(optional = false)
    @JoinColumn(name = UserModel_.CITY_ID, nullable = false)
    private CityModel city;

}