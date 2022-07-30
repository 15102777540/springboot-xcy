package com.springbootmall.pojo.dto;

import com.springbootmall.pojo.dto.base.BaseEntity;
import lombok.Data;
import org.mapstruct.Builder;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
public class Menu extends BaseEntity implements Serializable {
    private Long id;

    private Set<Role> roles;

    private String title;

    private String componentName;

    private Integer menuSort = 999;

    private String component;

    private String path;

    private Integer type;

    private String permission;

    private String icon;

    private Boolean cache;

    private Boolean hidden;

    private Long pid;

    private Integer subCount = 0;

    private Boolean iFrame;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

