package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.CovDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

@Mapper(imports = {BigDecimal.class})
public interface CovMapConverter {

    CovMapConverter INSTANCE = Mappers.getMapper(CovMapConverter.class);

    @Mappings({
            @Mapping(target = "province",expression = "java((String)map.get(\"Column1\"))"),
            @Mapping(target = "date",expression = "java((String)map.get(\"Column2\"))"),
            @Mapping(target = "confirmAdd",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column3\")))"),
            @Mapping(target = "confirm",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column4\")))"),
            @Mapping(target = "heal",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column5\")))"),
            @Mapping(target = "dead",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column6\")))"),
            @Mapping(target = "newdiagnose",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column7\")))"),
            @Mapping(target = "asymptomatic",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column8\")))"),
            @Mapping(target = "asymptomaticAdd",expression = "java(BigDecimal.valueOf((Double)map.get(\"Column9\")))"),
    })
    CovDto toCovDto(Map map);

    List<CovDto> toCovDtoList(List<Map> mapList);
}
