package com.sidorovich.pavel.ris.ejb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discount_code")
public class DiscountCode {

    @Id
    @Column(name = "code", nullable = false, length = 1)
    private String code;

    @Column(name = "discount", nullable = false)
    private Integer discount;

}
