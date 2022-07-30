package com.springbootmall.service.impl;

import com.springbootmall.common.exception.EntityNotFoundException;
import com.springbootmall.javaBeanMapper.JobConverter;
import com.springbootmall.javaBeanMapper.RoleConverter;
import com.springbootmall.javaBeanMapper.UserConverter;
import com.springbootmall.mapper.UserMapper;
import com.springbootmall.pojo.dto.*;
import com.springbootmall.pojo.dto.base.RoleSmallDto;
import com.springbootmall.service.UserService;
import com.springbootmall.util.MyBeanUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public List<UserDto> queryAll(){
        List<User> users = userMapper.selectAll();
        List<UserDto> userDtos = UserConverter.INSTANCE.toMenuDtoList(users);
        return userDtos;
    };

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public void create(User resources) {

    }

    @Override
    public void update(User resources) throws Exception {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public UserDto findByName(String userName) {
        User user = userMapper.findByName(userName);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "username", userName);
        }
        /*try {
            BeanUtils.copyProperties(userDto, user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } ;*/
        //MyBeanUtils.copyProperties(user,userDto);
        //UserDto userDto = MyBeanUtils.copyProperties(user, UserDto.class);
       /* List<Role> roles = user.getRoles().stream().collect(Collectors.toList());
        List<Job> jobs = user.getJobs().stream().collect(Collectors.toList());
        Set<RoleSmallDto> RoleSmallDtoset = new HashSet<>(RoleConverter.INSTANCE.toMenuDtoList(roles));
        Set<JobSmallDto> JobSmall
        Dtoset = new HashSet<>(JobConverter.INSTANCE.toMenuDtoList(jobs));
        userDto.setRoles(RoleSmallDtoset);
        userDto.setJobs(JobSmallDtoset);*/
        //userDto.setDept(UserConverter.INSTANCE.toMenuDto());
        UserDto userDto = UserConverter.INSTANCE.toMenuDto(user);
        return userDto;
    }

    @Override
    public void updatePass(String username, String encryptPassword) {

    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public void updateEmail(String username, String email) {

    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public void updateCenter(User resources) {

    }
}
