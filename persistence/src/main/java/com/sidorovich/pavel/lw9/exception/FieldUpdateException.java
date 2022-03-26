package com.sidorovich.pavel.lw9.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class FieldUpdateException extends CommonEntityException {

    private final List<String> valuesByFields;

    public FieldUpdateException(Class<?> clazz, List<String> valuesByFields) {
        super(clazz);
        this.valuesByFields = valuesByFields;
    }

}
