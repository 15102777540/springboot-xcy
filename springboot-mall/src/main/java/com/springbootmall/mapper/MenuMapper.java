package com.springbootmall.mapper;

import com.springbootmall.pojo.dto.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashSet;
import java.util.Set;

public interface MenuMapper {

    LinkedHashSet<Menu> findByRoleIdsAndTypeNot(@Param("roleIds") Set<Long> roleIds, int type);
}
