package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.university.diplom.constants.FunctionType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDto {

    private double phaseDifference;
    private double amplitude;
    private double waveLength;
    private double step;
    private double amplitudeSecond;
    private double frequency;
    private double frequencySecond;
    private FunctionType type;
}
