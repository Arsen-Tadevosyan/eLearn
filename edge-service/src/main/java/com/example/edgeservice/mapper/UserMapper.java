package com.example.edgeservice.mapper;

import com.example.edgeservice.dto.UserRegisterDto;
import com.example.edgeservice.dto.UserResponseDto;
import com.example.edgeservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRegisterDto userRegisterDto);

    UserResponseDto map(User user);

}