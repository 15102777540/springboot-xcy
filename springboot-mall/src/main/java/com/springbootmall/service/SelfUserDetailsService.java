package com.springbootmall.service;

import com.springbootmall.common.exception.BadRequestException;
import com.springbootmall.common.exception.EntityNotFoundException;
import com.springbootmall.config.Security.bean.LoginProperties;
import com.springbootmall.mapper.SelfUserDetailsMapper;
import com.springbootmall.pojo.dto.JwtUserDto;
import com.springbootmall.pojo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 用户认证、权限
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private SelfUserDetailsMapper selfUserDetailsMapper;

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private DataService dataService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户信息缓存
     *
     * @see {@link UserCacheClean}
     */
    static Map<String, JwtUserDto> userDtoCache = new ConcurrentHashMap<>();

    //"{bcrypt}"
    @Override
    public JwtUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean searchDb = true;
        JwtUserDto jwtUserDto = null;
        if (loginProperties.isCacheEnable() && userDtoCache.containsKey(username)) {
            jwtUserDto = userDtoCache.get(username);
            // 检查dataScope是否修改
            List<Long> dataScopes = jwtUserDto.getDataScopes();
            dataScopes.clear();
            dataScopes.addAll(dataService.getDeptIds(jwtUserDto.getUser()));
            searchDb = false;
        }
        if (searchDb) {
            UserDto user;
            try {
                user = userService.findByName(username);
                user.setPassword("{bcrypt}"+user.getPassword());

                /*String password = "123456";
                //加密：bcryptPasswordEncoder进行密码加密c
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = bcryptPasswordEncoder.encode(password);
                System.out.println("bcryptPasswordEncoder进行密码加密:"+encodedPassword);

                //解密：
                boolean flag = bcryptPasswordEncoder.matches(password, encodedPassword);
                //如果flag为true，则解密成功  false，则解密失败
                System.out.println("解密："+flag);*/

            } catch (EntityNotFoundException e) {
                // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
                throw new UsernameNotFoundException("", e);
            }
            if (user == null) {
                throw new UsernameNotFoundException("");
            } else {
                if (!user.getEnabled()) {
                    throw new BadRequestException("账号未激活！");
                }
                jwtUserDto = new JwtUserDto(
                        user,
                        dataService.getDeptIds(user),
                        roleService.mapToGrantedAuthorities(user)
                );
                userDtoCache.put(username, jwtUserDto);
            }
        }
        return jwtUserDto;
    }
}
