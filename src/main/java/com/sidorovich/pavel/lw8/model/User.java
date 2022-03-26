package com.sidorovich.pavel.lw8.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlType(propOrder = { "id", "firstName", "isMale", "identificationNumber", "homeTelephone", "city" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @XmlAttribute
    private Long id;

    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;

    @Column(name = "is_male", nullable = false)
    private boolean isMale;

    @Column(name = "id_number", length = 6)
    private String identificationNumber;

    @Column(name = "home_tel", length = 8)
    private String homeTelephone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

}