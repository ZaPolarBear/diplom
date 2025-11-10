package org.university.diplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.university.diplom.model.AppUserDetails;
import org.university.diplom.model.UserEntity;
import org.university.diplom.repository.UserEntityRepository;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userEntityRepository.findUserEntityByUsername(username);
        return userEntity.map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username [%s] not found".formatted(username)));
    }
}
