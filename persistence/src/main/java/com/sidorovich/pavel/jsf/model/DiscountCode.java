package com.sidorovich.pavel.jsf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "discount_code")
public class DiscountCode {

    public DiscountCode() {
    }

    public DiscountCode(String code, Integer discount) {
        this.code = code;
        this.discount = discount;
    }

    @Id
    @Column(name = "code", nullable = false, length = 1)
    @Pattern(regexp = "^[A-Z]$", message = "Code should contain only one capital character")
    private String code;

    @Column(name = "discount", nullable = false)
    @Min(value = 1, message = "Min value is 1")
    @Max(value = 99, message = "Max value is 99")
    private Integer discount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscountCode that = (DiscountCode) o;
        return Objects.equals(code, that.code) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, discount);
    }

    @Override
    public String toString() {
        return "DiscountCode{" +
               "code='" + code + '\'' +
               ", discount=" + discount +
               '}';
    }

}
