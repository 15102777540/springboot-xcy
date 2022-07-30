package com.springbootmall.service.impl;

import com.springbootmall.mapper.RoleMapper;
import com.springbootmall.pojo.dto.Menu;
import com.springbootmall.pojo.dto.Role;
import com.springbootmall.pojo.dto.RoleDto;
import com.springbootmall.pojo.dto.UserDto;
import com.springbootmall.pojo.dto.base.RoleSmallDto;
import com.springbootmall.service.RoleService;
import com.springbootmall.util.MyBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDto> queryAll() {
        return null;
    }

    @Override
    public RoleDto findById(long id) {
        return null;
    }

    @Override
    public void create(Role resources) {

    }

    @Override
    public void update(Role resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        List<Role> list = roleMapper.findByUsersId(id);
        List list1 = MyBeanUtils.copyListProperties(list, RoleSmallDto.class);
        return list1;
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        return null;
    }

    @Override
    public void updateMenu(Role resources, RoleDto roleDTO) {

    }

    @Override
    public void untiedMenu(Long id) {

    }

    @Override
    public void download(List<RoleDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<Role> roles = (Set<Role>) roleMapper.findByUsersId(user.getId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public void verification(Set<Long> ids) {

    }

    @Override
    public List<Role> findInMenuId(List<Long> menuIds) {
        return null;
    }
}
