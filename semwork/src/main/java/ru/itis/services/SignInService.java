package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;

import java.util.Optional;

public interface SignInService {
    UserDto signIn(UserForm userForm);
}
