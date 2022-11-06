package com.order;

import com.api.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl  {

    @DubboReference
    OrderService orderService;

    public void buyTicket(){
        System.out.println("123123");
        String s =orderService.getOrder("123", "123", "546sdfsdfsdf", "qweqweqw");
        System.out.println("consumer说：\"我要票、、\""+"\n"+"provider说："+"\""+s+"\"");
    }
}
