package org.university.diplom.dto;

import java.io.Serializable;

public record RoleEntityDto(
        Long id,
        String name
) implements Serializable {
}
