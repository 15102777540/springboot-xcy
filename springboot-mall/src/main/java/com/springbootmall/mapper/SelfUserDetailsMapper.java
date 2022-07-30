package com.springbootmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootmall.pojo.SelfUserDetails;
import org.apache.ibatis.annotations.Param;

public interface SelfUserDetailsMapper  {

    SelfUserDetails selectByUserName(@Param("username") String UserName);
}
