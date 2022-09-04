package com.dubbo;

import com.dubbo.services.TicketService;
import com.dubbo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    void buyTicket() {
        userService.buyTicket();
    }
}