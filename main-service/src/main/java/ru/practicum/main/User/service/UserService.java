package ru.practicum.main.User.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.main.User.dto.NewUserRequest;
import ru.practicum.main.User.dto.UserDto;
import ru.practicum.main.User.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(NewUserRequest newUserRequest);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteUser(Long userId);

    UserDto getUserById(Long userId);

    User getEntityById(Long userId);

    Page<UserDto> getUsersPage(List<Long> ids, Pageable pageable);
}