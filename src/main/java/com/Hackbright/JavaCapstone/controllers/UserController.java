package com.Hackbright.JavaCapstone.controllers;


import com.Hackbright.JavaCapstone.dtos.UserDto;
import com.Hackbright.JavaCapstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        System.out.println("===================================================================");
        System.out.println("=================================,  addUser is called!!!");
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto) {
        System.out.println("===================================================================");
        System.out.println("=================================,  userLogin is called!!!");
        return userService.userLogin(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        System.out.println("===================================================================");
        System.out.println("=================================,  getAllUsers() is called!!!");
        List<UserDto> userDtoList = this.userService.findAll();
        displayUserDtoList(userDtoList);
        return userDtoList;
    }

    private void displayUserDtoList(List<UserDto> userDtoList) {
        System.out.println("================= the total userDto size =" + userDtoList.size());
        int index = 1;
        for(UserDto userDto : userDtoList){
            System.out.println("\t UserDto No.{}, = {}" + userDto);
            index++;
        }
    }

}
