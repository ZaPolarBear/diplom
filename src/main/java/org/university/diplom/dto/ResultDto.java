package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.university.diplom.constants.FunctionType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private String imageName;
    private String function;
    private byte[] imageBytes;
    private FunctionType type;
}
