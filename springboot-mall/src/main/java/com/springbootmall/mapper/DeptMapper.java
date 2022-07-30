package com.springbootmall.mapper;


import com.springbootmall.pojo.dto.Dept;

import java.util.List;

public interface DeptMapper {

    List<Dept> findByRoleId(Long id);

    List<Dept> findByPid(Long id);
}
