package com.springbootmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springbootmall.pojo.dto.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    List<User> selectAll();

    List<User> selectPage(IPage<User> page);

    User  findByName(String userName);
}
