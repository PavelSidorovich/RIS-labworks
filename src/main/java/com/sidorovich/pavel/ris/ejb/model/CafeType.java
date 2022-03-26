package com.sidorovich.pavel.ris.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CafeType {
    COFFEE_HOUSE("Coffee house"),
    BAKERY_CAFE("Bakery"),
    ICE_CREAM_CAFE("Ice-cream"),
    GRILLE_CAFE("Grille"),
    BAR_CAFE("Bar"),
    CAFETERIA("Cafeteria"),
    ALL("All");

    private final String type;

    public static CafeType getCafeType(String type) {
        return Arrays.stream(values())
                     .filter(cafeType -> cafeType.type.equals(type))
                     .findFirst().orElse(ALL);
    }

}
