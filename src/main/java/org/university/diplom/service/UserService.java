package org.university.diplom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.university.diplom.dto.AuthResponseDto;
import org.university.diplom.dto.UserLoginRequestDto;
import org.university.diplom.dto.UserRegisterRequestDto;
import org.university.diplom.mapper.UserEntityMapper;
import org.university.diplom.model.AppUserDetails;
import org.university.diplom.model.UserEntity;
import org.university.diplom.repository.RoleEntityRepository;
import org.university.diplom.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    private final UserEntityRepository userEntityRepository;

    private final UserEntityMapper userEntityMapper;

    private final RoleEntityRepository roleEntityRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final AppUserDetailsService appUserDetailsService;

    public void createUser(UserRegisterRequestDto userRegisterRequestDto) {
        UserEntity user = userEntityMapper.toEntity(userRegisterRequestDto);

        if (CollectionUtils.isEmpty(userEntityRepository.findAll())) {
            user.setRole(roleEntityRepository.getByName(ROLE_ADMIN));
        } else {
            user.setRole(roleEntityRepository.getByName(ROLE_USER));
        }
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
    }

    public AuthResponseDto loginUser(UserLoginRequestDto requestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                requestDto.getUsername(),
                requestDto.getPassword()
        ));

        AppUserDetails userDetails = appUserDetailsService.loadUserByUsername(requestDto.getUsername());

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return new AuthResponseDto(accessToken, refreshToken);
    }

}
