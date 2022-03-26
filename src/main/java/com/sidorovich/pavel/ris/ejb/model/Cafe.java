package com.sidorovich.pavel.ris.ejb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cafe")
@Entity
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cafe_name", unique = true, nullable = false)
    private String name;

    @Column(name = "cafe_type")
    @Enumerated(EnumType.STRING)
    private CafeType type;

    @Column(name = "table_amount", nullable = false)
    private int tableAmount;

    @Column(name = "avg_cost", nullable = false)
    private BigDecimal avgCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cafe cafe = (Cafe) o;
        return id == cafe.id && tableAmount == cafe.tableAmount && Objects.equals(name, cafe.name) &&
               Objects.equals(avgCost, cafe.avgCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tableAmount, avgCost);
    }

}
