package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Job;
import com.springbootmall.pojo.dto.JobSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface JobConverter {

    JobConverter INSTANCE = Mappers.getMapper(JobConverter.class);

    /*@Mappings({
            @Mapping(target = "userName",source = "name"),
            @Mapping(target = "status",expression = "java(java.lang.String.valueOf(user.getStatus()))"),
            @Mapping(target = "createTime",dateFormat="yyyy-MM-dd HH:mm")
    })*/
    JobSmallDto toMenuDto(Job menu);

    List<JobSmallDto> toMenuDtoList(List<Job> menuList);
}
