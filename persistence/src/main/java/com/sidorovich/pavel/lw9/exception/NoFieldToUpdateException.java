package com.sidorovich.pavel.lw9.exception;

public class NoFieldToUpdateException extends CommonEntityException {

    public NoFieldToUpdateException(Class<?> clazz) {
        super(clazz);
    }

}
