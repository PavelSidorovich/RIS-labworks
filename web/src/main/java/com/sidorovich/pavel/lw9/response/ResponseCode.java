package com.sidorovich.pavel.lw9.response;

import com.sidorovich.pavel.lw9.dto.CityDto;
import com.sidorovich.pavel.lw9.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    CITY("01", CityDto.class),
    USER("02", UserDto.class),
    DEFAULT("00", Object.class);

    private final String postfixCode;
    private final Class<?> clazzForPostfix;

    public static String findPostfixByClass(Class<?> clazz) {
        return Arrays.stream(values())
                     .filter(code -> code.clazzForPostfix.equals(clazz))
                     .findAny()
                     .map(ResponseCode::getPostfixCode)
                     .orElseGet(DEFAULT::getPostfixCode);
    }

}
