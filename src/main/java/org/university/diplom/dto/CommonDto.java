package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.validation.ValidFunction;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDto {

    @ValidFunction
    @NotBlank
    private double amplitude;

    @ValidFunction
    @NotBlank
    private double waveLength;

    @ValidFunction
    @NotBlank
    private double step;

    @NotBlank
    private FunctionType type;

    private CommonDto commonDto;

}
