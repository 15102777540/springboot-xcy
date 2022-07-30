package com.springbootmall.pojo.dto;

import com.springbootmall.pojo.dto.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Job extends BaseEntity implements Serializable {

    private Long id;

    private String name;

    private Long jobSort;

    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}