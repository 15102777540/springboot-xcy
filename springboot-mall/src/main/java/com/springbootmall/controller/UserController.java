package com.springbootmall.controller;

import com.springbootmall.pojo.dto.UserDto;
import com.springbootmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> query(int page,int size,String sort){
        List<UserDto> userDtos = userService.queryAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
