package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Dept;
import com.springbootmall.pojo.dto.DeptSmallDto;
import com.springbootmall.pojo.dto.Job;
import com.springbootmall.pojo.dto.JobSmallDto;
import com.springbootmall.pojo.dto.Role;
import com.springbootmall.pojo.dto.User;
import com.springbootmall.pojo.dto.UserDto;
import com.springbootmall.pojo.dto.base.RoleSmallDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T15:05:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDto toMenuDto(User menu) {
        if ( menu == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCreateBy( menu.getCreateBy() );
        userDto.setUpdateBy( menu.getUpdateBy() );
        userDto.setCreateTime( menu.getCreateTime() );
        userDto.setUpdateTime( menu.getUpdateTime() );
        userDto.setId( menu.getId() );
        userDto.setRoles( roleSetToRoleSmallDtoSet( menu.getRoles() ) );
        userDto.setJobs( jobSetToJobSmallDtoSet( menu.getJobs() ) );
        userDto.setDept( deptToDeptSmallDto( menu.getDept() ) );
        userDto.setUsername( menu.getUsername() );
        userDto.setNickName( menu.getNickName() );
        userDto.setEmail( menu.getEmail() );
        userDto.setPhone( menu.getPhone() );
        userDto.setGender( menu.getGender() );
        userDto.setAvatarName( menu.getAvatarName() );
        userDto.setAvatarPath( menu.getAvatarPath() );
        userDto.setPassword( menu.getPassword() );
        userDto.setEnabled( menu.getEnabled() );
        userDto.setIsAdmin( menu.getIsAdmin() );
        userDto.setPwdResetTime( menu.getPwdResetTime() );

        return userDto;
    }

    @Override
    public List<UserDto> toMenuDtoList(List<User> menuList) {
        if ( menuList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( menuList.size() );
        for ( User user : menuList ) {
            list.add( toMenuDto( user ) );
        }

        return list;
    }

    protected RoleSmallDto roleToRoleSmallDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( role.getId() );
        roleSmallDto.setName( role.getName() );
        roleSmallDto.setLevel( role.getLevel() );
        roleSmallDto.setDataScope( role.getDataScope() );

        return roleSmallDto;
    }

    protected Set<RoleSmallDto> roleSetToRoleSmallDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleSmallDto> set1 = new HashSet<RoleSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleSmallDto( role ) );
        }

        return set1;
    }

    protected JobSmallDto jobToJobSmallDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobSmallDto jobSmallDto = new JobSmallDto();

        jobSmallDto.setId( job.getId() );
        jobSmallDto.setName( job.getName() );

        return jobSmallDto;
    }

    protected Set<JobSmallDto> jobSetToJobSmallDtoSet(Set<Job> set) {
        if ( set == null ) {
            return null;
        }

        Set<JobSmallDto> set1 = new HashSet<JobSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Job job : set ) {
            set1.add( jobToJobSmallDto( job ) );
        }

        return set1;
    }

    protected DeptSmallDto deptToDeptSmallDto(Dept dept) {
        if ( dept == null ) {
            return null;
        }

        DeptSmallDto deptSmallDto = new DeptSmallDto();

        deptSmallDto.setId( dept.getId() );
        deptSmallDto.setName( dept.getName() );

        return deptSmallDto;
    }
}
