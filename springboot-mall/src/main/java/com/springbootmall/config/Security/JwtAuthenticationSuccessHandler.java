package com.springbootmall.config.Security;

import com.alibaba.fastjson.JSON;
import com.springbootmall.pojo.Enums.ResultEnum;
import com.springbootmall.pojo.SelfUserDetails;
import com.springbootmall.util.JwtTokenUtil;
import com.springbootmall.util.ResultJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 用户登录成功时返回给前端的数据
 */

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.secret}")
    private String key;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();

        String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), 1500);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultJson.result(ResultEnum.USER_LOGIN_SUCCESS,jwtToken,true)));
    }
}
