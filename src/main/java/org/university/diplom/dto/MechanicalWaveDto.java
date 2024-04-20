package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.university.diplom.constants.FunctionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicalWaveDto {

    private double amplitude;
    private double waveLength;
    private double step;
    private FunctionType type;

}
