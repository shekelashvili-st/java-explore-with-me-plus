package ru.practicum.main.User.mapper;

import ru.practicum.main.User.dto.NewUserRequest;
import ru.practicum.main.User.dto.UserDto;
import ru.practicum.main.User.dto.UserShortDto;
import ru.practicum.main.User.entity.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(),user.getEmail(), user.getName());
    }

    public static UserShortDto toUserShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }

    public static User toUser(NewUserRequest newUserRequest) {
        User user = new User();
        user.setEmail(newUserRequest.getEmail());
        user.setName(newUserRequest.getName());
        return user;
    }

    public static User toUser(UserDto userDto) {
        return userDto == null ? null :
                new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
