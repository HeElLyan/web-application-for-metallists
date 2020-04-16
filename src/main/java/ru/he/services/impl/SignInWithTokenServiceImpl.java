package ru.he.services.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;
import ru.he.models.entities.Token;
import ru.he.models.entities.User;
import ru.he.repositoriesJpa.TokensRepository;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.services.SignInWithTokenService;

import java.util.Optional;

@Component
public class SignInWithTokenServiceImpl implements SignInWithTokenService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDto signIn(AuthDto authDto) {
        Optional<User> userCandidate = usersRepository.findByEmail(authDto.getEmail());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokensRepository.save(token);
                return UserDto.from(user);
            }
        } throw new IllegalArgumentException("User not found");
    }
}
