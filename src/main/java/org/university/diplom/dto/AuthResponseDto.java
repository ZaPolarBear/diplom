package org.university.diplom.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {
}
