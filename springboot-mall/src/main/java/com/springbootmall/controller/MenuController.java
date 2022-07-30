package com.springbootmall.controller;

import com.springbootmall.pojo.dto.MenuDto;
import com.springbootmall.service.MenuService;
import com.springbootmall.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/build")
    public ResponseEntity<Object> buildMenus(){
        List<MenuDto> menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        List<MenuDto> menuDtos = menuService.buildTree(menuDtoList);
        return new ResponseEntity<>(menuService.buildMenus(menuDtos), HttpStatus.OK);
    }
}
