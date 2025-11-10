package org.university.diplom.dto;

import java.io.Serializable;
import java.util.UUID;

public record UserEntityDto(
        UUID id,
        String username,
        String surname,
        String firstname,
        String lastname,
        String password,
        RoleEntityDto role
) implements Serializable {
}
