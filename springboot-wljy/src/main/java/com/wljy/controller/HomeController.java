package com.wljy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * 访问首页提示
     *
     * @return /
     */
    @RequestMapping(value = "/",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String index() {
        return "欢迎来到学习网站工具站";
    }
}
