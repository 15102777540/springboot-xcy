package com.springbootmall.pojo.dto;

import com.springbootmall.pojo.dto.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * 用户
 */
@Data
public class User extends BaseEntity implements Serializable {

    private Long id;

    private Set<Role> roles;

    private Set<Job> jobs;

    private Dept dept;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    private String password;

    private Boolean enabled;

    private Boolean isAdmin = false;

    private Date pwdResetTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
