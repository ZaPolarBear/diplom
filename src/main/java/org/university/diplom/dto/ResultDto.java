package org.university.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 453134L;

    private String imageName;
    private String function;
    private String fileName;
}
