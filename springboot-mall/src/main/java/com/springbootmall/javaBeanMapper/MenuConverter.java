package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Menu;
import com.springbootmall.pojo.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface MenuConverter  {

    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    /*@Mappings({
            @Mapping(target = "userName",source = "name"),
            @Mapping(target = "status",expression = "java(java.lang.String.valueOf(user.getStatus()))"),
            @Mapping(target = "createTime",dateFormat="yyyy-MM-dd HH:mm")
    })*/
    MenuDto toMenuDto(Menu menu);

    List<MenuDto> toMenuDtoList(List<Menu> menuList);
}
