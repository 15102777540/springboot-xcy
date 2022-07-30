package com.springbootmall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthUserDto implements Serializable {

    private String username;

    private String password;

    private String code;

    private String uuid = "";
}
