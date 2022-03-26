package com.sidorovich.pavel.jsf.filter;

import java.util.Objects;

public class DiscountCodeFilter {

    private String code;
    private Integer discount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.isEmpty()? null : code;
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
        DiscountCodeFilter that = (DiscountCodeFilter) o;
        return Objects.equals(code, that.code) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, discount);
    }

    @Override
    public String toString() {
        return "DiscountCodeFilter{" +
               "code='" + code + '\'' +
               ", discount=" + discount +
               '}';
    }

}
