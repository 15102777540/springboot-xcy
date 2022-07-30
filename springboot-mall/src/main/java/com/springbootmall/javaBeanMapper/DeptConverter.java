package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Dept;
import com.springbootmall.pojo.dto.DeptSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface DeptConverter {

    DeptConverter INSTANCE = Mappers.getMapper(DeptConverter.class);

    /*@Mappings({
            @Mapping(target = "userName",source = "name"),
            @Mapping(target = "status",expression = "java(java.lang.String.valueOf(user.getStatus()))"),
            @Mapping(target = "createTime",dateFormat="yyyy-MM-dd HH:mm")
    })*/
    DeptSmallDto toMenuDto(Dept menu);

    List<DeptSmallDto> toMenuDtoList(List<Dept> menuList);
}
