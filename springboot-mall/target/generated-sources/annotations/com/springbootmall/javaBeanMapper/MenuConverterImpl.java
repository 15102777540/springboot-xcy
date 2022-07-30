package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Menu;
import com.springbootmall.pojo.dto.MenuDto;
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
public class MenuConverterImpl implements MenuConverter {

    @Override
    public MenuDto toMenuDto(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setCreateBy( menu.getCreateBy() );
        menuDto.setUpdateBy( menu.getUpdateBy() );
        menuDto.setCreateTime( menu.getCreateTime() );
        menuDto.setUpdateTime( menu.getUpdateTime() );
        menuDto.setId( menu.getId() );
        menuDto.setType( menu.getType() );
        menuDto.setPermission( menu.getPermission() );
        menuDto.setTitle( menu.getTitle() );
        menuDto.setMenuSort( menu.getMenuSort() );
        menuDto.setPath( menu.getPath() );
        menuDto.setComponent( menu.getComponent() );
        menuDto.setPid( menu.getPid() );
        menuDto.setSubCount( menu.getSubCount() );
        menuDto.setIFrame( menu.getIFrame() );
        menuDto.setCache( menu.getCache() );
        menuDto.setHidden( menu.getHidden() );
        menuDto.setComponentName( menu.getComponentName() );
        menuDto.setIcon( menu.getIcon() );

        return menuDto;
    }

    @Override
    public List<MenuDto> toMenuDtoList(List<Menu> menuList) {
        if ( menuList == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( menuList.size() );
        for ( Menu menu : menuList ) {
            list.add( toMenuDto( menu ) );
        }

        return list;
    }
}
