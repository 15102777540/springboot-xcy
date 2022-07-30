package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.CovDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T15:05:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class CovMapConverterImpl implements CovMapConverter {

    @Override
    public CovDto toCovDto(Map map) {
        if ( map == null ) {
            return null;
        }

        CovDto covDto = new CovDto();

        covDto.setProvince( (String)map.get("Column1") );
        covDto.setDate( (String)map.get("Column2") );
        covDto.setConfirmAdd( BigDecimal.valueOf((Double)map.get("Column3")) );
        covDto.setConfirm( BigDecimal.valueOf((Double)map.get("Column4")) );
        covDto.setHeal( BigDecimal.valueOf((Double)map.get("Column5")) );
        covDto.setDead( BigDecimal.valueOf((Double)map.get("Column6")) );
        covDto.setNewdiagnose( BigDecimal.valueOf((Double)map.get("Column7")) );
        covDto.setAsymptomatic( BigDecimal.valueOf((Double)map.get("Column8")) );
        covDto.setAsymptomaticAdd( BigDecimal.valueOf((Double)map.get("Column9")) );

        return covDto;
    }

    @Override
    public List<CovDto> toCovDtoList(List<Map> mapList) {
        if ( mapList == null ) {
            return null;
        }

        List<CovDto> list = new ArrayList<CovDto>( mapList.size() );
        for ( Map map : mapList ) {
            list.add( toCovDto( map ) );
        }

        return list;
    }
}
