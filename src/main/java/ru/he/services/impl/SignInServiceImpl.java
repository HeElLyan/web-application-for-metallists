package ru.he.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;
import ru.he.models.entities.User;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.services.SignInService;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto signIn(AuthDto form) {
        String email = form.getEmail();
        String password = form.getPassword();

        User candidate = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String expected = candidate.getPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(password, expected);
        if (!isPasswordCorrect) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return UserDto.from(candidate);
    }

}
