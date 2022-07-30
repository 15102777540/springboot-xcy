package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.User;
import com.springbootmall.pojo.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /*@Mappings({
            @Mapping(target = "userName",source = "name"),
            @Mapping(target = "status",expression = "java(java.lang.String.valueOf(user.getStatus()))"),
            @Mapping(target = "createTime",dateFormat="yyyy-MM-dd HH:mm")
    })*/
    UserDto toMenuDto(User menu);

    List<UserDto> toMenuDtoList(List<User> menuList);
}
