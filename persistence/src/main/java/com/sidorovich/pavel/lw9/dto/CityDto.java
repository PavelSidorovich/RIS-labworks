package com.sidorovich.pavel.lw9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CityDto {

    @Null(message = "{model.field.not.null}")
    private Long id;

    @NotBlank(message = "{model.field.null}")
    @Size(max = 256, message = "{model.field.size.max}")
    private String cityName;

}
