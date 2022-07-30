package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Cov;
import com.springbootmall.pojo.dto.CovDto;
import com.springbootmall.pojo.dto.User;
import com.springbootmall.pojo.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface CovConverter {

    CovConverter INSTANCE = Mappers.getMapper(CovConverter.class);

    /*@Mappings({
            @Mapping(target = "province",source = "Column1"),
            @Mapping(target = "confirmAdd",source = "Column2"),
            @Mapping(target = "confirm",source = "Column3"),
            @Mapping(target = "heal",source = "Column4"),
            @Mapping(target = "dead",source = "Column5"),
            @Mapping(target = "newdiagnose",source = "Column6"),
            @Mapping(target = "asymptomatic",source = "Column7"),
            @Mapping(target = "asymptomaticAdd",source = "Column8"),
    })*/
    CovDto toCovDto(Cov cov);

    List<CovDto> toCovDtoList(List<Cov> covList);

    Cov toCov(CovDto cov);

    List<Cov> toCovList(List<CovDto> covList);
}
