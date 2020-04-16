package ru.he.services;

import ru.he.dto.AuthDto;
import ru.he.dto.TokenDto;
import ru.he.dto.UserDto;

public interface SignInWithTokenService {
    UserDto signIn(AuthDto authDto);
}
