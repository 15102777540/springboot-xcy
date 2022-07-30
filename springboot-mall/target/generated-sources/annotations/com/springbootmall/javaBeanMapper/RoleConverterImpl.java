package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Role;
import com.springbootmall.pojo.dto.base.RoleSmallDto;
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
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleSmallDto toMenuDto(Role menu) {
        if ( menu == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( menu.getId() );
        roleSmallDto.setName( menu.getName() );
        roleSmallDto.setLevel( menu.getLevel() );
        roleSmallDto.setDataScope( menu.getDataScope() );

        return roleSmallDto;
    }

    @Override
    public List<RoleSmallDto> toMenuDtoList(List<Role> menuList) {
        if ( menuList == null ) {
            return null;
        }

        List<RoleSmallDto> list = new ArrayList<RoleSmallDto>( menuList.size() );
        for ( Role role : menuList ) {
            list.add( toMenuDto( role ) );
        }

        return list;
    }
}
