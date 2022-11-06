package com;

import com.api.OrderService;
import com.dubbo.services.TicketService;
import com.dubbo.services.UserService;
import com.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    /*@Autowired
    UserService userService;*/

    @Autowired
    OrderServiceImpl orderService;

    @Test
    void buyTicket() {
        //userService.buyTicket();
    }

    @Test
    void buyTickets() {
        orderService.buyTicket();
    }
}