package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Role;
import com.springbootmall.pojo.dto.base.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    /*@Mappings({
            @Mapping(target = "userName",source = "name"),
            @Mapping(target = "status",expression = "java(java.lang.String.valueOf(user.getStatus()))"),
            @Mapping(target = "createTime",dateFormat="yyyy-MM-dd HH:mm")
    })*/
    RoleSmallDto toMenuDto(Role menu);

    List<RoleSmallDto> toMenuDtoList(List<Role> menuList);
}
