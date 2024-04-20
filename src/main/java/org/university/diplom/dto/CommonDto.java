package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.university.diplom.constants.FunctionType;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDto {

    @NotBlank
    private double amplitude;

    @NotBlank
    private double waveLength;

    @NotBlank
    private double step;

    @NotBlank
    private FunctionType type;

    private CommonDto commonDto;

}
