package com.springbootmall.mapper;

import com.springbootmall.pojo.dto.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> findByUsersId(Long id);
}
