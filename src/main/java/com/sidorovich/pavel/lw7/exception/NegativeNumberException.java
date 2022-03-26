package com.sidorovich.pavel.lw7.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class NegativeNumberException extends RuntimeException {

    private final int number;

}
