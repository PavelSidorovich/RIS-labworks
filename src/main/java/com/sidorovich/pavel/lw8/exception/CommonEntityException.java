package com.sidorovich.pavel.lw8.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonEntityException extends RuntimeException {

    private final Class<?> clazz;

}
