package com.sidorovich.pavel.lw8.repository.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonColumn {

    ID("id");

    private final String columnName;

}
