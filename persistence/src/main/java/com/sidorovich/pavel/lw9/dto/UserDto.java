package com.sidorovich.pavel.lw9.dto;

import com.sidorovich.pavel.lw9.validator.CreateValidationGroup;
import com.sidorovich.pavel.lw9.validator.UpdateValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserDto {

    @Null(message = "{model.field.not.null}", groups = { CreateValidationGroup.class })
    @NotNull(message = "{model.field.null}", groups = { UpdateValidationGroup.class })
    private Long id;

    @NotBlank(message = "{model.field.null}", groups = { CreateValidationGroup.class })
    @Size(max = 256, message = "{model.field.size.max}", groups = { CreateValidationGroup.class })
    private String firstName;

    @NotNull(message = "{model.field.null}", groups = { CreateValidationGroup.class })
    private Boolean isMale;

    @Pattern(regexp = "^([A-Z]{2}[\\d]{2}[A-Z]{2})?$", message = "{id.number.invalid}",
            groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private String idNumber;

    @Pattern(regexp = "^([\\d]{2}-[\\d]{2}-[\\d]{2})?$", message = "{tel.number.invalid}",
            groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private String homeTel;

    @Valid
    @NotNull(message = "{model.field.null}", groups = { CreateValidationGroup.class })
    private CityDto city;

}

