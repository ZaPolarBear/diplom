package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.data.xy.XYSeriesCollection;
import org.university.diplom.constants.FunctionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaveDto {

    private FunctionType type;
    private XYSeriesCollection dataset;

}
