package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Dept;
import com.springbootmall.pojo.dto.DeptSmallDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T15:05:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class DeptConverterImpl implements DeptConverter {

    @Override
    public DeptSmallDto toMenuDto(Dept menu) {
        if ( menu == null ) {
            return null;
        }

        DeptSmallDto deptSmallDto = new DeptSmallDto();

        deptSmallDto.setId( menu.getId() );
        deptSmallDto.setName( menu.getName() );

        return deptSmallDto;
    }

    @Override
    public List<DeptSmallDto> toMenuDtoList(List<Dept> menuList) {
        if ( menuList == null ) {
            return null;
        }

        List<DeptSmallDto> list = new ArrayList<DeptSmallDto>( menuList.size() );
        for ( Dept dept : menuList ) {
            list.add( toMenuDto( dept ) );
        }

        return list;
    }
}
