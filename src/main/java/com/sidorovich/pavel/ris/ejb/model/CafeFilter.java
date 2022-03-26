package com.sidorovich.pavel.ris.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CafeFilter {

    private CafeType cafeType;
    private BigDecimal minCost;
    private BigDecimal maxCost;

}
