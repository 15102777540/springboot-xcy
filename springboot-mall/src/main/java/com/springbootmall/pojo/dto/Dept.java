package com.springbootmall.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.springbootmall.pojo.dto.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
public class Dept extends BaseEntity implements Serializable {

    private Long id;

    private Set<Role> roles;

    private Integer deptSort;

    private String name;

    private Boolean enabled;

    private Long pid;

    private Integer subCount = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dept dept = (Dept) o;
        return Objects.equals(id, dept.id) &&
                Objects.equals(name, dept.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
