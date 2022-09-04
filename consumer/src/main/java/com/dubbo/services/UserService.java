package com.dubbo.services;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @DubboReference
    TicketService ticketService;

    public void buyTicket(){
        System.out.println("123123");
        String s =ticketService.getTicket();
        System.out.println("consumer说：\"我要票、、\""+"\n"+"provider说："+"\""+s+"\"");
    }

}
