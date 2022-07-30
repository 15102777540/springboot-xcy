package com.springbootmall.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.springbootmall.pojo.Enums.DataScopeEnum;
import com.springbootmall.pojo.dto.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 角色
 */
@Data
public class Role extends BaseEntity implements Serializable {

    private Long id;

    private Set<User> users;

    private Set<Menu> menus;

    private Set<Dept> depts;

    private String name;

    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    private Integer level = 3;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
