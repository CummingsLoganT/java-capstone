package com.Hackbright.JavaCapstone.services;

import com.Hackbright.JavaCapstone.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(UserDto userDto);

    List<UserDto> findAll();

}
