package com.sidorovich.pavel.lw9.exception;

public class DuplicatePropertyException extends EntityFieldException {

    public DuplicatePropertyException(Class<?> clazz, String entityField, Object fieldValue) {
        super(clazz, entityField, fieldValue);
    }

}
